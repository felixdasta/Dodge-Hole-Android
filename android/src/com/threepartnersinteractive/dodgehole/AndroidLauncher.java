package com.threepartnersinteractive.dodgehole;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import androidx.annotation.NonNull;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidGraphics;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesClient;
import com.google.android.gms.games.LeaderboardsClient;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayersClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;

public class AndroidLauncher extends AndroidApplication implements GameEssentials {

	private static final String TAG = "Dodge Hole";

	private RelativeLayout layout;
	private RelativeLayout.LayoutParams params;

	private static final String BANNER_AD_UNIT_ID = "ca-app-pub-3940256099942544/6300978111";
	private static final String INTERSTITIAL_AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712";

	private AdView banner_ad;
	private InterstitialAd interstitial_ad;

	// Client used to sign in with Google APIs
	private GoogleSignInClient mGoogleSignInClient;
	private GoogleSignInAccount mGoogleSignInAccount;

	// Client variables
	private LeaderboardsClient mLeaderboardsClient;
	private PlayersClient mPlayersClient;

	// request codes we use when invoking an external activity
	private static final int RC_UNUSED = 5001;
	private static final int RC_SIGN_IN = 9001;
	private static final int RC_LEADERBOARD_UI = 9004;

	// Firebase Analytics
	private FirebaseAnalytics mFirebaseAnalytics;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		config.useImmersiveMode = true;

		this.createGoogleClient();

		View game_view = initializeForView(new DodgeHole(this), config);

		////////// Define the layout
		layout = new RelativeLayout(this);
		layout.addView(game_view, ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);

		params = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);

		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);

		this.setupAds();

		layout.addView(banner_ad, params);
		setContentView(layout);
	}

	// Create the client used to sign in to Google services.
	private void createGoogleClient(){
		mGoogleSignInClient = GoogleSignIn.getClient(this,
				new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).build());

		mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
	}


	private void setupAds(){
		banner_ad = new AdView(this);
		banner_ad.setVisibility(View.INVISIBLE);
		banner_ad.setAdUnitId(BANNER_AD_UNIT_ID);
		banner_ad.setAdSize(AdSize.SMART_BANNER);

		interstitial_ad = new InterstitialAd(this);
		interstitial_ad.setAdUnitId(INTERSTITIAL_AD_UNIT_ID);
		interstitial_ad.loadAd(new AdRequest.Builder().build());
		interstitial_ad.setAdListener(new AdListener() {
			@Override
			public void onAdClosed() {
				// Load the next interstitial.
				interstitial_ad.loadAd(new AdRequest.Builder().build());
			}
		});
	}


	@Override
	public void showBannerAd() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				banner_ad.loadAd(new AdRequest.Builder().build());
				banner_ad.setVisibility(View.VISIBLE);
			}
		});
	}

	@Override
	public void hideBannerAd() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				banner_ad.setVisibility(View.INVISIBLE);
			}
		});
	}

	@Override
	public void showInterstitialAd() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if(interstitial_ad.isLoaded()) interstitial_ad.show();
			}
		});
	}


	private void startSignInIntent() {
		Log.d(TAG, "startSignInIntent");
		startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_SIGN_IN);
	}

	private void signOut() {
		Log.d(TAG, "signOut()");

		if (!isSignedIn()) {
			Log.w(TAG, "signOut() called, but was not signed in!");
			return;
		}

		mGoogleSignInClient.signOut().addOnCompleteListener(this,
				new OnCompleteListener<Void>() {
					@Override
					public void onComplete(@NonNull Task<Void> task) {
						boolean successful = task.isSuccessful();
						Log.d(TAG, "signOut(): " + (successful ? "success" : "failed"));

						onDisconnected();
					}
				});

	}

	@Override
	public void signInSilently() {
		Log.d(TAG, "signInSilently()");

		mGoogleSignInClient.silentSignIn().addOnCompleteListener(this,
				new OnCompleteListener<GoogleSignInAccount>() {
					@Override
					public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
						if (task.isSuccessful()) {
							Log.d(TAG, "signInSilently(): success");
							onConnected(task.getResult());
						} else {
							Log.d(TAG, "signInSilently(): failure", task.getException());
							onDisconnected();
						}
					}
				});
	}

	public boolean isSignedIn() {
		Log.d(TAG, "isSignedIn= " + (GoogleSignIn.getLastSignedInAccount(this) != null));
		return GoogleSignIn.getLastSignedInAccount(this) != null;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (requestCode == RC_SIGN_IN) {
			Task<GoogleSignInAccount> task =
					GoogleSignIn.getSignedInAccountFromIntent(intent);

			try {
				GoogleSignInAccount account = task.getResult(ApiException.class);
				onConnected(account);
			} catch (ApiException apiException) {
				String message = apiException.getMessage();
				if (message == null || message.isEmpty()) {
					message = getString(R.string.signin_other_error);
				}

				onDisconnected();

				new AlertDialog.Builder(this)
						.setMessage(message)
						.setNeutralButton(android.R.string.ok, null)
						.show();
			}
		}
	}

	private void onConnected(GoogleSignInAccount googleSignInAccount) {
		Log.d(TAG, "onConnected(): connected to Google APIs");

		mGoogleSignInAccount = googleSignInAccount;

		mLeaderboardsClient = Games.getLeaderboardsClient(this, googleSignInAccount);
		mPlayersClient = Games.getPlayersClient(this, googleSignInAccount);

		// Set the greeting appropriately on main menu
		mPlayersClient.getCurrentPlayer()
				.addOnCompleteListener(new OnCompleteListener<Player>() {
					@Override
					public void onComplete(@NonNull Task<Player> task) {
						String displayName;
						if (task.isSuccessful()) {
							displayName = task.getResult().getDisplayName();
						} else {
							Exception e = task.getException();
							handleException(e, getString(R.string.players_exception));
							displayName = "???";
						}
						GamesClient gamesClient = Games.getGamesClient(AndroidLauncher.this, mGoogleSignInAccount); //setGravityForPopups(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
						gamesClient.setGravityForPopups(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
						gamesClient.setViewForPopups(((AndroidGraphics) AndroidLauncher.this.getGraphics()).getView());
					}
				});
	}

	private void onDisconnected() {
		Log.d(TAG, "onDisconnected()");

		mLeaderboardsClient = null;
		mPlayersClient = null;
	}

	private void handleException(Exception e, String details) {
		int status = 0;

		if (e instanceof ApiException) {
			ApiException apiException = (ApiException) e;
			status = apiException.getStatusCode();
		}
	}

	@Override
	public void onSignInButtonClicked() {
		startSignInIntent();
	}

	@Override
	public void onSignOutButtonClicked() {
		signOut();
	}

	@Override
	public void submitScore(int highScore) {
		Log.d(TAG, "leaderboardId IN= " + getString(R.string.leaderboard_player_scores));
		if (isSignedIn()) {
			Log.d(TAG, "leaderboardId OUT= " + getString(R.string.leaderboard_player_scores));
			mLeaderboardsClient.submitScore(getString(R.string.leaderboard_player_scores), highScore);
		}
	}

	@Override
	public void showLeaderboard() {
		Log.d(TAG, "showLeaderboard IN= " + getString(R.string.leaderboard_player_scores));

		if (isSignedIn()) {
			Log.d(TAG, "showLeaderboard OUT= " + getString(R.string.leaderboard_player_scores));

			Games.getLeaderboardsClient(this, GoogleSignIn.getLastSignedInAccount(this))
					.getLeaderboardIntent(getString(R.string.leaderboard_player_scores))
					.addOnSuccessListener(new OnSuccessListener<Intent>() {
						@Override
						public void onSuccess(Intent intent) {
							startActivityForResult(intent, RC_LEADERBOARD_UI);
						}
					});
		}
	}

	@Override
	public void setTrackerScreenName(String screenName) {
		mFirebaseAnalytics.setCurrentScreen(this, screenName, null);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume()");

		// Since the state of the signed in user can change when the activity is not active
		// it is recommended to try and sign in silently from when the app resumes.
		signInSilently();
	}
}

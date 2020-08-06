package com.threepartnersinteractive.dodgehole;

public interface GameEssentials {
    //Google Admobs Services
    void showBannerAd();
    void hideBannerAd();
    void showInterstitialAd();

    //Google Game Services
    void onSignInButtonClicked();
    void onSignOutButtonClicked();
    void signInSilently();
    void submitScore(int highScore);
    void showLeaderboard();
    void setTrackerScreenName(String screenName);
    boolean isSignedIn();
}
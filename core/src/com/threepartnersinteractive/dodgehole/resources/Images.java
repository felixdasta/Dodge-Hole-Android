package com.threepartnersinteractive.dodgehole.resources;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Images {

    public static Texture title;
    public static Texture game_over;

    private Texture spriteSheet;
    public static Texture cowSheet;
    public static TextureRegion[] cow;

    public static TextureRegion car;
    public static TextureRegion truck;
    public static TextureRegion motorhome;

    private Texture water;
    public static TextureRegion[] water_anim;

    public static TextureRegion[] sand;
    public static TextureRegion salt;

    public static Texture street;
    public static Texture[] street_hole;
    public static Texture sign;

    public static TextureRegion tree;
    public static TextureRegion left_house;
    public static TextureRegion right_house;

    public Images() {
        cow = new TextureRegion[3];

        street_hole = new Texture[3];

        sand = new TextureRegion[7];

        water_anim = new TextureRegion[3];

        spriteSheet = new Texture("Sheets/gameSprites.png");
        cowSheet = new Texture("Sheets/cow.png");

        title = new Texture("Sheets/Title V2.png");
        game_over = new Texture("Sheets/Game Over.png");

        //cow
        cow[0] = new TextureRegion(cowSheet, 144, 112, 47, 32);
        cow[1] =  new TextureRegion(cowSheet,192, 111, 47, 33);
        cow[2] =  new TextureRegion(cowSheet,240, 112, 47, 32);


        street = new Texture("Sheets/street.png");

        street_hole[0] = new Texture("Sheets/street hole.png");
        street_hole[1] = new Texture("Sheets/street hole 2.png");
        street_hole[2] = new Texture("Sheets/street hole 3.png");

        sign = new Texture("Sheets/sign.png");

        //vehicles
        car = new TextureRegion(spriteSheet, 135, 104, 40 , 81);
        truck =  new TextureRegion(spriteSheet,182, 104, 51, 129);
        motorhome = new TextureRegion(spriteSheet, 307, 101, 59, 268);

        //nature
        tree =  new TextureRegion(spriteSheet,9, 4, 100, 106);
        salt = new TextureRegion(spriteSheet, 168, 266, 118, 103);


        sand[0] = new TextureRegion(spriteSheet, 18, 565, 124, 60);
        sand[1] = new TextureRegion(spriteSheet, 18, 504, 124, 60);
        sand[2] = new TextureRegion(spriteSheet, 18, 443, 92, 60);
        sand[3] = new TextureRegion(spriteSheet, 18, 382, 54, 60);
        sand[4] = new TextureRegion(spriteSheet, 18, 443, 92, 60);
        sand[5] = new TextureRegion(spriteSheet, 18, 504, 124, 60);
        sand[6] = new TextureRegion(spriteSheet, 18, 565, 124, 60);

        sand[0].flip(false, true);
        sand[1].flip(false, true);
        sand[2].flip(false, true);


        water = new Texture("Sheets/water.png");

        water_anim[0] =  new TextureRegion(water,0, 0, 32, 32);
        water_anim[1] =  new TextureRegion(water,32, 0, 32, 32);
        water_anim[2] =  new TextureRegion(water,64, 0, 32, 32);

        left_house = new TextureRegion(spriteSheet, 8, 191, 129, 184);

        right_house  = new TextureRegion(spriteSheet, 8, 191, 129, 184);
        right_house.flip(true, false);


    }

    /* Initializes this class in a static mode*/
    public static Images init(){
        return new Images();
    }

}

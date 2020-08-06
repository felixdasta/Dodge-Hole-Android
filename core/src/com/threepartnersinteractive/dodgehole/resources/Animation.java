package com.threepartnersinteractive.dodgehole.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Elemental on 12/19/2016.
 */
public class Animation {
    private int speed,index;
    private long lastTime,timer;
    private TextureRegion[] frames;

    public Animation(int speed,TextureRegion[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();

    }

    public Animation(int speed,TextureRegion[] frames,int index){
        this.speed=speed;
        this.frames=frames;
        this.index=index;
        timer = 0;
        lastTime = System.currentTimeMillis();

    }

    public void update(float dt){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > speed * dt){
            index++;
            timer = 0;
            if(index >= frames.length){
                index = 0;
            }
        }

    }

    public TextureRegion getCurrentFrame(){
        return frames[index];
    }

    public int getIndex() {
        return index;
    }

    public static String getLtr() {
        return "M";
    }

}
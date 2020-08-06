package com.threepartnersinteractive.dodgehole;

import java.util.Random;

public class MyRandom {

    private static Random random = new Random();

    public static int nextInt(int i){ return random.nextInt(i); }
}

package com.selenium.assignment.utils;

import java.util.Random;

public class RessourceUtil {

    public int calcRandom(  ) {
        Random rand = new Random(); //instance of random class
        int upperbound = 999;
        //generate random values from 0-99
        int int_random = rand.nextInt(upperbound);
        return int_random;

    }

}

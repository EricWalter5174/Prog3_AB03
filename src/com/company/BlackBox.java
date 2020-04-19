package com.company;

import java.util.Random;

public class BlackBox {
    Random rand = new Random();

    public Integer getData(){
        return rand.nextInt(500);
    }
}

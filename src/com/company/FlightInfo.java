package com.company;
/*
 * Autor: Eric Walter
 * Prog3 AB03
 * Hochschule Osnabrueck
 * */
import java.util.Random;

public class FlightInfo {
    public int height;
    public int speed;
    public float latitude;
    public float longitude;

    public FlightInfo(int height, int speed, float latitude, float longitude) {
        this.height = height;
        this.speed = speed;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "FlightInfo{" +
                "height=" + height +
                ", speed=" + speed +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public static FlightInfo Random() {
        Random r = new Random();

        return new FlightInfo(r.nextInt(10000), r.nextInt(1000), r.nextFloat()*180, r.nextFloat()*180);
    }
}
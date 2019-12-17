package com.myhome.models;

public abstract class BikeElectric extends Bike {
    private double speed;
    private int batCapacity;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getBatCapacity() {
        return batCapacity;
    }

    public void setBatCapacity(int batCapacity) {
        this.batCapacity = batCapacity;
    }


}

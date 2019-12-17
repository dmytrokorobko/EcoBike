package com.myhome.models;

import java.math.BigDecimal;

public class Speedelect extends BikeElectric {
    public Speedelect(String brand, double speed, double weight, boolean isLight, int batCapacity, String color, BigDecimal price) {
        setBrand(brand);
        setSpeed(speed);
        setWeight(weight);
        setLight(isLight);
        setBatCapacity(batCapacity);
        setColor(color);
        setPrice(price);
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public String toString() {
        String str = "SPEEDELECT " + getBrand() + " with " + getBatCapacity() + " mAh battery and ";
        String light = isLight() ? "" : "no ";
        str += light;
        str += "head/tail light.\nPrice: " + getPrice() + " euros.";
        return  str;
    }
}

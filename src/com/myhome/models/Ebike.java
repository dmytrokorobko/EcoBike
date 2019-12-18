package com.myhome.models;

import java.math.BigDecimal;

public class Ebike extends BikeElectric implements Bikes {
    public Ebike(String brand, double speed, double weight, boolean isLight, int batCapacity, String color, BigDecimal price) {
        setBrand(brand);
        setSpeed(speed);
        setWeight(weight);
        setLight(isLight);
        setBatCapacity(batCapacity);
        setColor(color);
        setPrice(price);
    }

    public Ebike(String brand, String speed, String weight, String light, String battery, String color, String price) {
        setBrand(brand);
        setSpeed(Double.parseDouble(speed.trim()));
        setWeight(Double.parseDouble(weight.trim()));
        setLight(light.trim().equals("true") ? true : false);
        setBatCapacity(Integer.parseInt(battery.trim()));
        setColor(color.trim());
        setPrice(BigDecimal.valueOf(Long.parseLong(price.trim())));
    }

    @Override
    public boolean equals(Object obj) {
        return this.getBrand().contains((String) obj);
    }

    @Override
    public String toString() {
        String str = "E-BIKE " + getBrand() + " with " + getBatCapacity() + " mAh battery and ";
        String light = isLight() ? "" : "no ";
        str += light;
        str += "head/tail light.\nPrice: " + getPrice() + " euros.";
        return  str;
    }
}

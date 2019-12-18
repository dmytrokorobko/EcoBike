package com.myhome.models;

import java.math.BigDecimal;

public class FoldingBike extends Bike implements Bikes {
    private double wheelSize;
    private byte gears;

    public FoldingBike(String brand, double wheelSize, byte gears, double weight, boolean isLight, String color, BigDecimal price) {
        setBrand(brand);
        this.wheelSize = wheelSize;
        this.gears = gears;
        setWeight(weight);
        setLight(isLight);
        setColor(color);
        setPrice(price);
    }

    public FoldingBike(String brand, String size, String gears, String weight, String light, String color, String price) {
        setBrand(brand);
        this.wheelSize = Double.parseDouble(size.trim());
        this.gears = Byte.parseByte(gears.trim());
        setWeight(Double.parseDouble(weight.trim()));
        setLight(light.trim().equals("true") ? true : false);
        setColor(color.trim());
        setPrice(BigDecimal.valueOf(Long.parseLong(price.trim())));
    }

    public double getWheelSize() {
        return wheelSize;
    }

    public void setWheelSize(double wheelSize) {
        this.wheelSize = wheelSize;
    }

    public byte getGears() {
        return gears;
    }

    public void setGears(byte gears) {
        this.gears = gears;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getBrand().contains((String) obj);
    }

    @Override
    public String toString() {
        String str = "FOLDING BIKE " + getBrand() + " with " + getGears() + " gear(s) and ";
        String light = isLight() ? "" : "no ";
        str += light;
        str += "head/tail light.\nPrice: " + getPrice() + " euros.";
        return  str;
    }
}

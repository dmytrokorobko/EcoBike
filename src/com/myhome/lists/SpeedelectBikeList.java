package com.myhome.lists;

import com.myhome.models.FoldingBike;
import com.myhome.models.Speedelect;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SpeedelectBikeList {
    private static SpeedelectBikeList instance;
    private static List<Speedelect> speedelectBikes;

    private SpeedelectBikeList() {
    }

    public static SpeedelectBikeList getInstance() {
        if (instance == null) {
            instance = new SpeedelectBikeList();
            speedelectBikes = new ArrayList<>();
        }
        return instance;
    }

    public List<Speedelect> getAll() {
        return speedelectBikes;
    }

    public FoldingBike getOne(String searchString) {
        return null;
    }

    public void addOne(Speedelect newBike) {
        speedelectBikes.add(newBike);
    }

    public void addOne(String brand, String speed, String weight, String light, String battery, String color, String price) {
        speedelectBikes.add(new Speedelect(
                brand,
                Double.parseDouble(speed.trim()),
                Double.parseDouble(weight.trim()),
                light.trim().equals("true") ? true : false,
                Integer.parseInt(battery.trim()),
                color.trim(),
                BigDecimal.valueOf(Long.parseLong(price.trim()))
        ));
    }
}

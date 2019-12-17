package com.myhome.lists;

import com.myhome.models.Ebike;
import com.myhome.models.FoldingBike;
import com.myhome.models.Speedelect;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EbikeList {
    private static EbikeList instance;
    private static List<Ebike> ebikes;

    private EbikeList() {
    }

    public static EbikeList getInstance() {
        if (instance == null) {
            instance = new EbikeList();
            ebikes = new ArrayList<>();
        }
        return instance;
    }

    public List<Ebike> getAll() {
        return ebikes;
    }

    public FoldingBike getOne(String searchString) {
        return null;
    }

    public void addOne(Ebike newBike) {
        ebikes.add(newBike);
    }

    public void addOne(String brand, String speed, String weight, String light, String battery, String color, String price) {
        ebikes.add(new Ebike(
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

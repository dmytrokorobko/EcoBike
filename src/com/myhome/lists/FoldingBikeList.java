package com.myhome.lists;

import com.myhome.models.FoldingBike;
import com.myhome.models.Speedelect;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FoldingBikeList {
    private static FoldingBikeList instance;
    private static List<FoldingBike> foldingBikes;

    private FoldingBikeList() {
    }

    public static FoldingBikeList getInstance() {
        if (instance == null) {
            instance = new FoldingBikeList();
            foldingBikes = new ArrayList<>();
        }
        return instance;
    }

    public List<FoldingBike> getAll() {
        return foldingBikes;
    }

    public FoldingBike getOne(String searchString) {
        return null;
    }

    public void addOne(FoldingBike newBike) {
        foldingBikes.add(newBike);
    }

    public void addOne(String brand, String size, String gears, String weight, String light, String color, String price) {
        foldingBikes.add(new FoldingBike(
                brand,
                Double.parseDouble(size.trim()),
                Byte.parseByte(gears.trim()),
                Double.parseDouble(weight.trim()),
                light.trim().equals("true") ? true : false,
                color.trim(),
                BigDecimal.valueOf(Long.parseLong(price.trim()))
        ));
    }

}

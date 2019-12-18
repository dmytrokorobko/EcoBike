package com.myhome;

import com.myhome.models.Bikes;
import com.myhome.models.Ebike;
import com.myhome.models.FoldingBike;
import com.myhome.models.Speedelect;

public class MySearch extends Thread {
    /*
    My method of search
     */

    private String searchLine;

    public MySearch() {
    }

    public void setSearchLine(String searchLine) {
        this.searchLine = searchLine;
    }

    @Override
    public void run() {
        Main.isSearching = true;
        String result = null;
        try {
            for (Bikes b : Main.bikesList) {
                if (b.equals(searchLine)) {
                    if (b instanceof FoldingBike) result = b.toString();
                    else if (b instanceof Speedelect) result = b.toString();
                    else if (b instanceof Ebike) result = b.toString();
                    break;
                }
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nSearch is completed");
        if (result != null) System.out.println(result);
        Main.isSearching = false;
    }
}

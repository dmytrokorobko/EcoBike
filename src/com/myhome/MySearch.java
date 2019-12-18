package com.myhome;

public class MySearch extends Thread {
    public MySearch() {
    }

    @Override
    public void run() {
        Main.isSearching = true;
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Main.isSearching = false;
    }
}

package com.myhome;

import com.myhome.lists.EbikeList;
import com.myhome.lists.FoldingBikeList;
import com.myhome.lists.SpeedelectBikeList;
import com.myhome.models.*;

import java.io.*;

public class Main {
    private static final String FILENAME = "ecobike.txt";
    private static final String SPLIT_SEP = ";";
    private static final String SPEEDLEC = "SPEEDELEC";
    private static final String FOLDING = "FOLDING BIKE";
    private static final String EBIKE = "E-BIKE";

    static SpeedelectBikeList speedelectBikeList;
    static FoldingBikeList foldingBikeList;
    static EbikeList ebikeList;


    public static void main(String[] args) throws IOException {
        speedelectBikeList = SpeedelectBikeList.getInstance();
        foldingBikeList = FoldingBikeList.getInstance();
        ebikeList = EbikeList.getInstance();

        readDataFromFile(null);
        showMenu();
    }

    private static void readDataFromFile(String currentPath) {
        String pathToFile = System.getProperty("user.dir") + File.separator + FILENAME;
        if (currentPath != null) pathToFile = currentPath;
        try(BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            long i = 0; //count imported data
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(SPLIT_SEP);
                if (data[0].contains(SPEEDLEC + " ")) {
                    speedelectBikeList.addOne(data[0].substring(SPEEDLEC.length()), data[1], data[2], data[3], data[4], data[5], data[6]);
                    i++;
                } else if (data[0].contains(FOLDING + " ")) {
                    foldingBikeList.addOne(data[0].substring(FOLDING.length()), data[1], data[2], data[3], data[4], data[5], data[6]);
                    i++;
                } else if (data[0].contains(EBIKE + " ")) {
                    ebikeList.addOne(data[0].substring(EBIKE.length()), data[1], data[2], data[3], data[4], data[5], data[6]);
                    i++;
                }
            }
            System.out.println("Imported " + i + " lines from file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showMenu() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        boolean isStop = false;
        while (!isStop) {
            //region menu items
            System.out.println("1 - Show the entire EcoBike catalog");
            System.out.println("2 - Add a new folding bike");
            System.out.println("3 - Add a new speedelect");
            System.out.println("4 - Add a new e-bike");
            System.out.println("5 - Find the first item of a particular brand");
            System.out.println("6 - Write to file");
            System.out.println("7 - Stop the program");
            //endregion

            int num = -1;
            try {
                System.out.print("Make your choice: ");
                num = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Enter correct number");
            }

            if (num < 1 || num > 7) {
                System.out.println("Enter correct number");
                continue;
            }

            switch (num) {
                case 1:
                    showAllBikes();
                    break;

                case 2:
                    //region input
                    System.out.print("Enter brand: ");
                    String brand = reader.readLine();
                    System.out.print("Enter size of wheels: ");
                    String size = reader.readLine();
                    System.out.print("Enter number of gears: ");
                    String gears = reader.readLine();
                    System.out.print("Enter weight: ");
                    String weight = reader.readLine();
                    System.out.print("Enter availability of lights(true or false): ");
                    String light = reader.readLine();
                    System.out.print("Enter color: ");
                    String color = reader.readLine();
                    System.out.print("Enter price: ");
                    String price = reader.readLine();
                    //endregion
                    foldingBikeList.addOne(brand, size, gears, weight, light, color, price);
                    break;

                case 3:
                    //region input
                    System.out.print("Enter brand: ");
                    brand = reader.readLine();
                    System.out.print("Enter maximum speed: ");
                    String speed = reader.readLine();
                    System.out.print("Enter weight: ");
                    weight = reader.readLine();
                    System.out.print("Enter availability of lights(true or false): ");
                    light = reader.readLine();
                    System.out.print("Enter battery capacity: ");
                    String battery = reader.readLine();
                    System.out.print("Enter color: ");
                    color = reader.readLine();
                    System.out.print("Enter price: ");
                    price = reader.readLine();
                    //endregion
                    speedelectBikeList.addOne(brand, speed, weight, light, battery, color, price);
                    break;

                case 4:
                    //region input
                    System.out.print("Enter brand: ");
                    brand = reader.readLine();
                    System.out.print("Enter maximum speed: ");
                    speed = reader.readLine();
                    System.out.print("Enter weight: ");
                    weight = reader.readLine();
                    System.out.print("Enter availability of lights(true or false): ");
                    light = reader.readLine();
                    System.out.print("Enter battery capacity: ");
                    battery = reader.readLine();
                    System.out.print("Enter color: ");
                    color = reader.readLine();
                    System.out.print("Enter price: ");
                    price = reader.readLine();
                    //endregion
                    ebikeList.addOne(brand, speed, weight, light, battery, color, price);
                    break;

                case 5:
                    String brandName = reader.readLine();
                    findBike(brandName);
                    break;

                case 6:
                    writeToFile();
                    break;

                case 7:
                    isStop = true;
                    break;
            }
        }

        reader.close();
    }

    private static void showAllBikes() {
        int i = 0;
        for(Bike b : foldingBikeList.getAll()) {
            System.out.println(b.toString());
            i++;
        }

        for(Bike b : speedelectBikeList.getAll()) {
            System.out.println(b.toString());
            i++;
        }

        for(Bike b : ebikeList.getAll()) {
            System.out.println(b.toString());
            i++;
        }
        System.out.println("Shown " + i + " items.");
    }

    private static Bike findBike(String string) {
        return null;
    }

    private static void writeToFile() {
        try {
            String path = System.getProperty("user.dir") + File.separator + FILENAME;
            FileWriter fileWriter = new FileWriter(path);
            for(FoldingBike b : foldingBikeList.getAll()) {
                String light = b.isLight() ? "true" : "false";
                fileWriter.write(FOLDING + " " + b.getBrand() + "; " + b.getWheelSize() + "; " + b.getGears() + "; " +
                        b.getWeight() + "; " + light + "; " + b.getColor() + "; " + b.getPrice() + "\n");
            }

            for(Speedelect b : speedelectBikeList.getAll()) {
                String light = b.isLight() ? "true" : "false";
                fileWriter.write(SPEEDLEC + " " + b.getBrand() + "; " + b.getSpeed() + "; " + b.getWeight() + "; " +
                        light + "; " + b.getBatCapacity() + "; " + b.getColor() + "; " + b.getPrice() + "\n");
            }

            for(Ebike b : ebikeList.getAll()) {
                String light = b.isLight() ? "true" : "false";
                fileWriter.write(EBIKE + " " + b.getBrand() + "; " + b.getSpeed() + "; " + b.getWeight() + "; " +
                        light + "; " + b.getBatCapacity() + "; " + b.getColor() + "; " + b.getPrice() + "\n");
            }
            fileWriter.close();
            System.out.println("All data has written to file: " + path + " successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

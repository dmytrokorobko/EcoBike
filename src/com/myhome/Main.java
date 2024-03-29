package com.myhome;

import com.myhome.models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String FILENAME = "ecobike.txt";
    private static final String SPLIT_SEP = ";";
    public static final String SPEEDLEC = "SPEEDELEC";
    public static final String FOLDING = "FOLDING BIKE";
    public static final String EBIKE = "E-BIKE";

    public static List<Bikes> bikesList = new ArrayList<>();
    public static boolean isSearching;

    public static void main(String[] args) throws IOException {
        readDataFromFile(null);
        showMenu();
    }

    private static void readDataFromFile(String currentPath) {
        String pathToFile = System.getProperty("user.dir") + File.separator + FILENAME;
        if (currentPath != null) pathToFile = currentPath;

        try(BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            long i = 0; //count of imported data
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(SPLIT_SEP);
                if (data[0].contains(SPEEDLEC + " ")) {
                    bikesList.add(new Speedelect(data[0].substring(SPEEDLEC.length()), data[1], data[2], data[3], data[4], data[5], data[6]));
                    i++;
                } else if (data[0].contains(FOLDING + " ")) {
                    bikesList.add(new FoldingBike(data[0].substring(FOLDING.length()), data[1], data[2], data[3], data[4], data[5], data[6]));
                    i++;
                } else if (data[0].contains(EBIKE + " ")) {
                    bikesList.add(new Ebike(data[0].substring(EBIKE.length()), data[1], data[2], data[3], data[4], data[5], data[6]));
                    i++;
                }
            }
            System.out.println("Imported " + i + " lines from file");
        } catch (FileNotFoundException e) {
            System.out.println("Such file wasn't found");
        } catch (IOException e) {
            System.out.println("Such file wasn't found");
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
            } catch (NumberFormatException e) {
                System.out.println("Enter correct number");
            } catch (IOException e) {
                System.out.println("Enter correct number");
            }

            if (num < 1 || num > 7) {
                System.out.println("Enter correct number in range between 1  and 7");
                continue;
            }

            if ((num == 2 || num == 3 || num == 4) && isSearching ) {
                System.out.println("You can't add new items while searching! Please try later.");
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
                    bikesList.add(new FoldingBike(brand, size, gears, weight, light, color, price));
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
                    bikesList.add(new Speedelect(brand, speed, weight, light, battery, color, price));
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
                    bikesList.add(new Ebike(brand, speed, weight, light, battery, color, price));
                    break;

                case 5:
                    System.out.print("Enter search line: ");
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
        for(Bikes b : bikesList) {
            System.out.println(b.toString());
            i++;
        }
        System.out.println("Shown " + i + " items.");
    }

    private static void findBike(String string) {
        MySearch search = new MySearch();
        search.setSearchLine(string);
        search.start();
    }

    private static void writeToFile() {
        try {
            String path = System.getProperty("user.dir") + File.separator + FILENAME;
            FileWriter fileWriter = new FileWriter(path);

            for(Bikes b : bikesList) {
                String light = ((Bike) b).isLight() ? "true" : "false";
                if (b instanceof FoldingBike) {
                    fileWriter.write(FOLDING + " " + ((FoldingBike) b).getBrand() + "; " +
                            ((FoldingBike) b).getWheelSize() + "; " + ((FoldingBike) b).getGears() + "; " +
                            ((FoldingBike) b).getWeight() + "; " + light + "; " +
                            ((FoldingBike) b).getColor() + "; " + ((FoldingBike) b).getPrice() + "\n");
                } else {
                    String str = "";
                    if (b instanceof Speedelect) str += SPEEDLEC + " ";
                    else str += EBIKE + " ";
                    str += ((BikeElectric) b).getBrand() + "; " + ((BikeElectric) b).getSpeed() + "; " +
                            ((BikeElectric) b).getWeight() + "; " + light + "; " +
                            ((BikeElectric) b).getBatCapacity() + "; " + ((BikeElectric) b).getColor() + "; " +
                            ((BikeElectric) b).getPrice() + "\n";
                    fileWriter.write(str);
                }

            }
            fileWriter.close();
            System.out.println("All data has written to file: " + path + " successfully");
        } catch (IOException e) {
            System.out.println("Data wasn't written to file");
        }
    }

}

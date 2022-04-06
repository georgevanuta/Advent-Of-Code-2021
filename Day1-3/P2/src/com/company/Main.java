package com.company;

public class Main {

    public static void main(String[] args) {
        String fileName = "src/com/company/input.txt";
        LifeSupportRatingOxygen lsro = new LifeSupportRatingOxygen();
        LifeSupportCO2Rating lsco2r = new LifeSupportCO2Rating();
        String O2 = lsro.getOxygenRating(fileName);
        String CO2 = lsco2r.getCO2Rating(fileName);
        System.out.println(O2);
        System.out.println(CO2);
        // multiply them in decimal to get answer
    }


}

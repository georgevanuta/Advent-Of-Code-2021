package com.company;

public class Main {

    public static void main(String[] args) {
        CrabsPositions crabsPositions = new CrabsPositions();
        crabsPositions.initializeFromFile("src/com/company/input.txt");
        System.out.println(crabsPositions.getCorrectMinimumFuelConsumption());
    }
}

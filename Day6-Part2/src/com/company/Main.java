package com.company;

public class Main {

    public static void main(String[] args) {
        SchoolOfFish school = new SchoolOfFish();
        school.initializeFromFile("src/com/company/input.txt");
        school.letNDaysGoBy(256);
        school.printNumberOfFish();
    }
}

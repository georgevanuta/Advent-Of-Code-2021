package com.company;

public class Main {

    public static void main(String[] args) {
        SchoolOfLanternFish school = new SchoolOfLanternFish();
        school.initializeFromFile("src/com/company/input.txt");
        school.countdownNDays(80);
        school.printNumberOfLanternFish();
    }
}

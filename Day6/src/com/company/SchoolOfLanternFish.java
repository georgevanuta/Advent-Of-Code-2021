package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SchoolOfLanternFish {
    private ArrayList<Integer> fish;
    private Integer currentSize;
    private final Integer DAYS_RESET = 6;
    private final Integer DAYS_AFTER_BORN = 8;


    public SchoolOfLanternFish() {
        fish = new ArrayList<Integer>();
        currentSize = 0;
    }

    public void initializeFromFile(String fileName) {
        File file = new File(fileName);

        try {
            Scanner scanner = new Scanner(file);

            String initFish[] = scanner.nextLine().split(",");
            List<String> fishString = Arrays.asList(initFish);

            for (String s : fishString) {
                this.fish.add(Integer.parseInt(s));
            }

            this.currentSize = this.fish.size();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void print() {
        for (int i = 0; i < currentSize; i++) {
            System.out.print(fish.get(i));
            if (i != currentSize - 1) {
                System.out.print(",");
            }
        }
        System.out.println();
    }

    public void countdownOneDay() {
        for (int i = 0; i < currentSize; i++) {
            if (fish.get(i) != 0) {
                fish.set(i, fish.get(i) - 1);
                continue;
            }

            fish.set(i, DAYS_RESET);
            fish.add(DAYS_AFTER_BORN);
        }

        this.currentSize = fish.size();
    }

    public void countdownNDays(Integer n) {
        for (int i = 0; i < n; i++) {
            System.out.println("Day " + (i+1));
            countdownOneDay();
        }
    }

    public void printNumberOfLanternFish() {
        System.out.println(this.currentSize);
    }
}

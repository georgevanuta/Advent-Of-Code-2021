package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class DepthIncreaseChecker {
    private Integer current;
    private Integer numberOfIncreases;

    public DepthIncreaseChecker() {
        numberOfIncreases = 0;
    }

    private void checkIfIncreased(Integer depth) {
        if (current == null) {
            current = depth;
            return;
        }
        else if (depth > current) {
            numberOfIncreases++;
        }
        current = depth;
    }

    public void readLogFromFile(String fileName) {
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                this.checkIfIncreased(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Integer getNumberOfIncreases() {
        return this.numberOfIncreases;
    }
}

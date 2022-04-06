package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Depth3IncreaseChecker {
    private Integer current;
    private Integer nrOfIncreases;

    public Depth3IncreaseChecker() {
        nrOfIncreases = 0;
    }

    public Integer getTotal() {
        return this.nrOfIncreases;
    }

    public void checkIfIncreased(Integer newDepth) {
        if (current == null) {
            current = newDepth;
            return;
        }

        if (newDepth > current) {
            nrOfIncreases++;
        }

        current = newDepth;
    }

    private void setCurrent(Integer depth) {
        current = depth;
    }

    public void readFromFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        try {
            Scanner scannerPrevious = new Scanner(file);
            Scanner scannerFuture = new Scanner(file);
            scannerFuture.nextInt();
            scannerFuture.nextInt();
            scannerFuture.nextInt();

            while (scannerFuture.hasNextLine()) {
                setCurrent(scannerPrevious.nextInt());
                checkIfIncreased(scannerFuture.nextInt());
                System.out.println(current);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

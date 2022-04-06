package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DigitSumDecoder {
    Integer sum;

    public DigitSumDecoder() {
        sum = 0;
    }

    public void decodeSumFromFile(String fileName) {
        File file = new File(fileName);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                OutputDigits outputDigits = new OutputDigits();
                outputDigits.decodeString(s);

                sum = sum + outputDigits.getOutputNumber();
            }
            System.out.println("Done decoding!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Integer getSum() {
        return sum;
    }
}

package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowerConsumptionCalculator {
    private String mostCommon;
    private String leastCommon;
    private Double mostCommonInteger;
    private Double leastCommonInteger;

    public PowerConsumptionCalculator() {
        mostCommon = new String();
        leastCommon = new String();
    }

    public void mostCommonNthBit(Integer n, List<String> allBits) {
        Integer whichIsMoreCommon = 0;  // positive for 1/ negative for 0

        for (int i = 0; i < allBits.size(); i++) {
            if (allBits.get(i).charAt(n) == '0') {
                whichIsMoreCommon--;
            } else {
                whichIsMoreCommon++;
            }
        }

        if (whichIsMoreCommon > 0) {
            mostCommon = mostCommon + "1";
            leastCommon = leastCommon + "0";
        } else {
            mostCommon = mostCommon + "0";
            leastCommon = leastCommon + "1";
        }
    }

    public void readPowerConsumptionsFromFile(String fileName) {
        try {
            String fileContent = Files.readString(Path.of(fileName));
            String fileSplit[] = fileContent.split("\n");
            List<String> arrayFileSplit = Arrays.asList(fileSplit);

            Integer bitsLength = arrayFileSplit.get(0).length() - 1;
            for (int i = 0; i < bitsLength; i++) {
                mostCommonNthBit(i, arrayFileSplit);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMostCommon() {
        return mostCommon;
    }

    public String getLeastCommon() {
        return leastCommon;
    }

    private static double toDecimal(String binaryString) {
        double decimalNumber = 0;
        for (int i = binaryString.length() - 1; i >= 0; i--) {
            if (binaryString.charAt(i) == '0') continue;

            decimalNumber = decimalNumber + Math.pow(2, binaryString.length() - 1 - i);
        }
        return decimalNumber;
    }

    private void convertToDecimal() {
        mostCommonInteger = toDecimal(mostCommon);
        leastCommonInteger = toDecimal(leastCommon);
    }

    public Double getTotalPower() {
        convertToDecimal();
        return mostCommonInteger * leastCommonInteger;
    }


}

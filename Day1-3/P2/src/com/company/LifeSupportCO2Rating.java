package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LifeSupportCO2Rating {
    private String CO2Rating;
    /* Contents of file are put into a list to make things easier */
    private List<String> bitList;


    /* Read and transform file to list */
    private void readDiagnosisFromFile(String fileName) {
        try {
            String fileAsString = Files.readString(Path.of(fileName));
            bitList = Arrays.asList(fileAsString.split("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Deletes numbers which don't contain "zeroOrOne" at whichBit position from bitList*/
    private void narrowDiagosis(Integer whichBit, char zeroOrOne) {
        List<String> filtered = bitList.stream()
                .filter(b -> b.charAt(whichBit) == zeroOrOne)
                .collect(Collectors.toList());
        bitList = filtered;
    }

    /* :ooks for the least frequent bit on position n in every element of bitList */
    private char leastFrequentNthBit(Integer n) {
        Integer zeroOrOne = 0; // negative for 1/ otherwise for 0

        for (int i = 0; i < bitList.size(); i++) {
            if (bitList.get(i).charAt(n) == '0') {
                zeroOrOne--;
            } else {
                zeroOrOne++;
            }
        }

        if (zeroOrOne >= 0) {
            return '0';
        }
        return '1';
    }

    /* Narrows down the list multiple times until there is only 1 element left */
    private void calculateCO2Rating() {
        final Integer lengthOfNumber = bitList.get(0).length() - 1;
        for (int i = 0; i < lengthOfNumber && bitList.size() != 1; i++) {
            char c = leastFrequentNthBit(i);
            this.narrowDiagosis(i, c);
        }
    }

    /* Stores value in CO2Rating and returns it*/
    public String getCO2Rating(String fileName) {
        readDiagnosisFromFile(fileName);
        calculateCO2Rating();
        CO2Rating = bitList.get(0);
        return CO2Rating;
    }
}

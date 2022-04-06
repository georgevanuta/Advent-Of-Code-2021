package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LifeSupportRatingOxygen {
    private String oxygenRating;
    private List<String> bitList;

    public LifeSupportRatingOxygen() {
        oxygenRating = new String();
        bitList = new ArrayList<String>();
    }

    private void readDiagnosisFromFile(String fileName) {
        try {
            String fileAsString = Files.readString(Path.of(fileName));
            bitList = Arrays.asList(fileAsString.split("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printList() {
        for (int i = 0; i < bitList.size(); i++) {
            System.out.println(bitList.get(i));
        }
    }

    /* Deletes numbers which don't contain "zeroOrOne" at whichBit position from bitList*/
    private void narrowDiagosis(Integer whichBit, char zeroOrOne) {
        List<String> filtered = bitList.stream()
                .filter(b -> b.charAt(whichBit) == zeroOrOne)
                .collect(Collectors.toList());
        bitList = filtered;
    }

    private char mostFrequentNthBit(Integer n) {
        Integer zeroOrOne = 0; //positive or 0 for 1/ negative for 0

        for (int i = 0; i < bitList.size(); i++) {
            if (bitList.get(i).charAt(n) == '0') {
                zeroOrOne--;
            } else {
                zeroOrOne++;
            }
        }

        if (zeroOrOne >= 0) {
            return '1';
        }
        return '0';
    }
    /* Returns most frequent Nth bit from bitList */

    private void calculateOxygenRating() {
        final Integer lengthOfNumber = bitList.get(0).length() - 1;
        for (int i = 0; i < lengthOfNumber && bitList.size() != 1; i++) {
            char c = mostFrequentNthBit(i);
            this.narrowDiagosis(i, c);
        }
    }

    public String getOxygenRating(String fileName) {
        readDiagnosisFromFile(fileName);
        calculateOxygenRating();
        oxygenRating = bitList.get(0);
        return oxygenRating;
    }
}

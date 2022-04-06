package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DigitsCounter {
    private Integer nrOfEastDigits;

    /* Constructor */
    public DigitsCounter() {
        nrOfEastDigits = 0;
    }

    /* length 2 -> 1, length 3 -> 7, length 4 -> 4, length 7 -> 8 */
    private boolean isStringEasyDigit(String s) {
        if (s.length() == 2 || s.length() == 3 || s.length() == 4 || s.length() == 7) {
            return true;
        }
        return false;
    }

    /* checks for easy digits in a string consisting of more digits */
    private void readEastDigitsFromString(String s) {
        List<String> stringSplit = Arrays.asList(s.split(" "));
        for (String s1 : stringSplit) {
            if (isStringEasyDigit(s1)) {
                nrOfEastDigits++;
            }
        }
    }

    /* reads digits from file/ only from output */
    public void readEasyDigitsFromFile(String fileName) {
        File file = new File(fileName);

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String s = scanner.nextLine().split("\\|")[1];
                readEastDigitsFromString(s);
            }
            System.out.println(nrOfEastDigits);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

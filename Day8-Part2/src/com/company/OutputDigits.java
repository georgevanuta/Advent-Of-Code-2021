package com.company;

public class OutputDigits {
    private InputDigits inputDigits;
    private Integer outputNumber;

    /* Constants */
    private final Integer NUMBER_OF_DIGITS = 10;
    private final Integer NUMBER_OF_OUTPUT_DIGITS = 4;

    public OutputDigits() {
        inputDigits = new InputDigits();
        outputNumber = 0;
    }

    private Boolean checkIfStringsHaveTheSameChars(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        for (int i = 0; i < s1.length(); i++) {
            if (!s1.contains("" + s2.charAt(i))) return false;
        }
        return true;
    }

    private Integer getDecodedDigit(String codedOutput) {
        for (int i = 0; i < NUMBER_OF_DIGITS; i++) {
            if (checkIfStringsHaveTheSameChars(codedOutput, inputDigits.getDecodedNumber(i))) {
                return i;
            }
        }
        System.out.println("ERROR");
        return -1;
    }

    public void decodeString(String string) {
        String input = string.split(" \\| ")[0];
        inputDigits.decodeString(input);

        String outputSplit[] = string.split(" \\| ")[1].split(" ");
        Integer decimalPlace = 1000;

        for (int i = 0; i < NUMBER_OF_OUTPUT_DIGITS; i++) {
            outputNumber = outputNumber + decimalPlace * getDecodedDigit(outputSplit[i]);
            decimalPlace = decimalPlace / 10;
        }
    }

    public Integer getOutputNumber() {
        return outputNumber;
    }

}

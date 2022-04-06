package com.company;

import java.util.ArrayList;

public class InputDigits {
    /* To better understand the mapping
    *  look into printMapping method and use it */
    private ArrayList<Character> mapping;

    /* Coded Numbers */
    private ArrayList<String> codedNumbers;

    /* Constants */
    private final Integer NUMBER_OF_WIRES = 7;
    private final Integer NUMBER_OF_DIGITS = 10;

    /* Constructor */
    public InputDigits() {
        mapping = new ArrayList<Character>();
        for (int i = 0; i < NUMBER_OF_WIRES; i++) {
            mapping.add('o');   /* default value */
        }

        codedNumbers = new ArrayList<String>();
        for (int i = 0; i < NUMBER_OF_DIGITS; i++) {
            codedNumbers.add("o");  /* default value */
        }
    }

    /* Easy digits are easy to identify, as each has a precise
    * number of wires */
    private void readEasyDigits(String inputString) {
        String inputStringSplit[] = inputString.split(" ");
        for (int i = 0; i < NUMBER_OF_DIGITS; i++) {
            Integer length = inputStringSplit[i].length();
            switch (length) {
                case 2:
                    codedNumbers.set(1, inputStringSplit[i]);
                    break;
                case 3:
                    codedNumbers.set(7, inputStringSplit[i]);
                    break;
                case 4:
                    codedNumbers.set(4, inputStringSplit[i]);
                    break;
                case 7:
                    codedNumbers.set(8, inputStringSplit[i]);
                    break;
                default:
                    break;
            }
        }
    }

    /* Getter */
    public String getDecodedNumber(Integer index) {
        return codedNumbers.get(index);
    }

    /* For debugging */
    public void printMapping() {
        System.out.print(" ");
        for (int i = 0; i < 4; i++) {
            System.out.print(mapping.get(0));
        }
        System.out.println();
        for (int i = 0; i < 2; i++) {
            System.out.println(mapping.get(1) + "    " + mapping.get(2));
        }
        System.out.print(" ");
        for (int i = 0; i < 4; i++) {
            System.out.print(mapping.get(3));
        }
        System.out.println();
        for (int i = 0; i < 2; i++) {
            System.out.println(mapping.get(4) + "    " + mapping.get(5));
        }
        System.out.print(" ");
        for (int i = 0; i < 4; i++) {
            System.out.print(mapping.get(6));
        }
        System.out.println();
    }

    public void printAllDigits() {
        System.out.println("zero -> " + codedNumbers.get(0));
        System.out.println("one -> " + codedNumbers.get(1));
        System.out.println("two -> " + codedNumbers.get(2));
        System.out.println("three -> " + codedNumbers.get(3));
        System.out.println("four -> " + codedNumbers.get(4));
        System.out.println("five -> " + codedNumbers.get(5));
        System.out.println("six -> " + codedNumbers.get(6));
        System.out.println("seven -> " + codedNumbers.get(7));
        System.out.println("eight -> " + codedNumbers.get(8));
        System.out.println("nine -> " + codedNumbers.get(9));

    }
    /* --- */

    private Boolean checkIfStringHasChar(String s, Character c) {
        if (s.contains("" + c)) return true;
        return false;
    }

    /* First one */
    /* Uses one and seven */
    private void decodeFirst() {
        if (codedNumbers.get(1).length() != 2 || codedNumbers.get(7).length() != 3) {
            System.out.println("One of them is not one or seven!");
            return;
        }
        int i;
        for (i = 0; i < codedNumbers.get(7).length(); i++) {
            if (checkIfStringHasChar(codedNumbers.get(1), codedNumbers.get(7).charAt(i))) continue;
            break;
        }

        mapping.set(0, codedNumbers.get(7).charAt(i));
    }

    /* Checks if s1 contains s2 in any order */
    private Boolean checkIfStringContainsString(String s1, String s2) {
        for (int i = 0; i < s2.length(); i++) {
            if (!s1.contains("" + s2.charAt(i))) return false;
        }
        return true;
    }

    private void readNineString(String inputString) {
        String inputStringSplit[] = inputString.split(" ");
        for (int i = 0; i < NUMBER_OF_DIGITS; i++) {
            if (inputStringSplit[i].length() == 6) {
                if (inputStringSplit[i].contains("" + mapping.get(0))) {
                    if (checkIfStringContainsString(inputStringSplit[i],codedNumbers.get(4))) {
                        codedNumbers.set(9, inputStringSplit[i]);
                        break;
                    }
                }
            }
        }
    }

    /* Second one */
    /* Uses four and nine */
    private void decodeSecond() {
        for (int i = 0; i < codedNumbers.get(9).length(); i++) {
            if (!codedNumbers.get(4).contains("" + codedNumbers.get(9).charAt(i))) {
                if (codedNumbers.get(9).charAt(i) != mapping.get(0)) {
                    mapping.set(NUMBER_OF_WIRES - 1, codedNumbers.get(9).charAt(i));
                    break;
                }
            }
        }
    }

    /* Third one */
    /* Used eight and nine */
    private void decodeThird() {
        for (int i = 0; i < codedNumbers.get(8).length(); i++) {
            if (!codedNumbers.get(9).contains("" + codedNumbers.get(8).charAt(i))) {
                mapping.set(NUMBER_OF_WIRES - 3, codedNumbers.get(8).charAt(i));
            }
        }
    }

    private void readThree(String inputString) {
        String inputStringSplit[] = inputString.split(" ");
        for (int i = 0; i < NUMBER_OF_DIGITS; i++) {
            if (inputStringSplit[i].length() == NUMBER_OF_WIRES - 2) {
                if (checkIfStringContainsString(inputStringSplit[i], codedNumbers.get(1))) {
                    codedNumbers.set(3, inputStringSplit[i]);
                    break;
                }
            }
        }
    }

    /* Fourth One */
    /* Uses three and other known wires */
    private void decodeFourth() {
        String auxString = codedNumbers.get(1) + mapping.get(0) + mapping.get(NUMBER_OF_WIRES - 1);
        for (int i = 0; i < codedNumbers.get(3).length(); i++) {
            if (!auxString.contains("" + codedNumbers.get(3).charAt(i))) {
                mapping.set(NUMBER_OF_WIRES - 4, codedNumbers.get(3).charAt(i));
                break;
            }
        }
    }

    private void readTwo(String inputString) {
        String inputStringSplit[] = inputString.split(" ");
        String strinxAux = "" + mapping.get(0) + mapping.get(3) +
                mapping.get(NUMBER_OF_WIRES - 1) + mapping.get(NUMBER_OF_WIRES - 3);

        for (int i = 0; i < NUMBER_OF_DIGITS; i++) {
            if (inputStringSplit[i].length() == 5) {
                if (checkIfStringContainsString(inputStringSplit[i], strinxAux)) {
                    codedNumbers.set(2, inputStringSplit[i]);
                    break;
                }
            }
        }
    }

    /* Fifth one */
    /* Uses two and other known wires */
    private void decodeFifth() {
        String strinxAux = "" + mapping.get(0) + mapping.get(3) +
                mapping.get(NUMBER_OF_WIRES - 1) + mapping.get(NUMBER_OF_WIRES - 3);

        for (int i = 0; i < codedNumbers.get(2).length(); i++) {
            if (!strinxAux.contains("" + codedNumbers.get(2).charAt(i))) {
                mapping.set(2, codedNumbers.get(2).charAt(i));
                break;
            }
        }
    }

    /* Sixth One */
    /* Uses one and two */
    private void decodeSixth() {
        for (int i = 0; i < codedNumbers.get(1).length(); i++) {
            if (!codedNumbers.get(2).contains("" + codedNumbers.get(1).charAt(i))) {
                mapping.set(NUMBER_OF_WIRES - 2, codedNumbers.get(1).charAt(i));
            }
        }
    }

    /* Seventh one */
    /* Uses eight and the other known wires */
    private void decodeSeventh() {
        String s = "";
        for (int i = 0; i < NUMBER_OF_WIRES; i++) {
            if (mapping.get(i) != 'o') {
                s = s + mapping.get(i);
            }
        }
        for (int i = 0; i < codedNumbers.get(8).length(); i++) {
            if (!s.contains("" + codedNumbers.get(8).charAt(i))) {
                mapping.set(1, codedNumbers.get(8).charAt(i));
                break;
            }
        }
    }

    /* Reads the rest of the numbers */
    public void readFive() {
        String five = "" + mapping.get(0) + mapping.get(1) + mapping.get(3) +
                mapping.get(5) + mapping.get(6);
        codedNumbers.set(5, five);
    }

    public void readSix() {
        String six = "" + mapping.get(0) + mapping.get(1) + mapping.get(3) +
                mapping.get(4) + mapping.get(5) + mapping.get(6);
        codedNumbers.set(6, six);
    }

    public void readZero() {
        String zero = "" + mapping.get(0) + mapping.get(1) + mapping.get(2) +
                mapping.get(4) + mapping.get(5) + mapping.get(6);
        codedNumbers.set(0, zero);
    }
    /* --- */

    /* Decodes every number */
    public void decodeString(String inputString) {
        readEasyDigits(inputString);
        decodeFirst();
        readNineString(inputString);
        decodeSecond();
        decodeThird();
        readThree(inputString);
        decodeFourth();
        readTwo(inputString);
        decodeFifth();
        decodeSixth();
        decodeSeventh();
        readFive();
        readSix();
        readZero();
    }
}

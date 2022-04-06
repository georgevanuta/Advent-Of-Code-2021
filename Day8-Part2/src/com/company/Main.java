package com.company;

public class Main {

    public static void main(String[] args) {
        DigitSumDecoder digitSumDecoder = new DigitSumDecoder();
        digitSumDecoder.decodeSumFromFile("src/com/company/input.txt");
        System.out.println(digitSumDecoder.getSum());
    }
}

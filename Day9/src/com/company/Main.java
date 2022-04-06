package com.company;

public class Main {

    public static void main(String[] args) {
        SmokeMatrix smokeMatrix = new SmokeMatrix();
        smokeMatrix.readMatrixFromFile("src/com/company/input.txt");
        System.out.println(smokeMatrix.getSumOfLowestPoints());
    }
}

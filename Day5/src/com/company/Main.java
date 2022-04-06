package com.company;

public class Main {

    public static void main(String[] args) {
        VentsMatrix ventsMatrix = new VentsMatrix();

        /* ---PART 1--- */
        ventsMatrix.readFromFileStraightLines("src/com/company/test.txt");

        /* ---PART 2--- */
        //ventsMatrix.readAllLinesFromFile("src/com/company/test.txt");\

        System.out.println(ventsMatrix.getPointsCrossedAtLeastNTimes(2));

        /* If you want to see how the matrix looks */
        //ventsMatrix.print();
    }
}

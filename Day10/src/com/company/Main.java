package com.company;

public class Main {

    public static void main(String[] args) {
        SyntaxChecker syntaxChecker = new SyntaxChecker();
        syntaxChecker.readLinesFromFile("src/com/company/input.txt");
        /* ---FOR PART 1--- */
//        syntaxChecker.calculateIllegalScore();
//        syntaxChecker.printScorePart1();
        /* ---FOR PART 2--- */
        syntaxChecker.calculateIncompleteScore();
        syntaxChecker.printScorePart2();
    }
}

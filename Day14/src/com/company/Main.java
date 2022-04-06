package com.company;

import com.company.polymer.Polymer;

public class Main {

    public static void main(String[] args) {
        /* Initialize polymer */
        Polymer polymer = new Polymer();
        polymer.readFromFile("src/com/company/tests/input.txt");

        /* ---PART 1--- */
        /* Doesn't work for part 2, too slow */
        //polymer.take10Steps();
        polymer.take40steps();
        /* result */
        polymer.printResult();
    }
}

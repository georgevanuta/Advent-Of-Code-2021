package com.company;

public class Main {

    public static void main(String[] args) {
        EnergyMatrix energyMatrix = new EnergyMatrix();
        energyMatrix.readFromFile("src/com/company/input.txt");
        /* PART 1 */
//        energyMatrix.takeALeap();
//        energyMatrix.printResultPart1();
        /* PART 2 */
        energyMatrix.printHowManyStepsTillSynchronizedFlashing();
    }
}

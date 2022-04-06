package com.company;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
//        DepthIncreaseChecker d = new DepthIncreaseChecker();
//        d.readLogFromFile("src/com/company/DepthLogger");
//        System.out.println(d.getNumberOfIncreases());

//        SubmarinePositionCalculator submarinePositionCalculator = new SubmarinePositionCalculator();
//        submarinePositionCalculator.readInstructions("src/com/company/submarineInstructions.txt");
//        System.out.println(submarinePositionCalculator.getPosition());

//        SubmarineAimCalculator submarineAimCalculator = new SubmarineAimCalculator();
//        submarineAimCalculator.readInstructionsFromFile("src/com/company/submarineInstructions.txt");
//        System.out.println(submarineAimCalculator.getTotal());

/*        Depth3IncreaseChecker depth3IncreaseChecker = new Depth3IncreaseChecker();
        depth3IncreaseChecker.readFromFile("src/com/company/DepthLogger");*/
//        System.out.println(depth3IncreaseChecker.getTotal());

        PowerConsumptionCalculator powerConsumptionCalculator = new PowerConsumptionCalculator();
        powerConsumptionCalculator.readPowerConsumptionsFromFile("src/com/company/powerConsumptionLogger.txt");
        System.out.println(powerConsumptionCalculator.getTotalPower());
    }
}

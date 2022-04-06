package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SubmarineAimCalculator {
    private Integer aim;
    private Integer horizPos;
    private Integer depthPos;

    public SubmarineAimCalculator() {
        aim = 0;
        horizPos = 0;
        depthPos = 0;
    }

    private void up(Integer units) {
        aim = aim - units;
    }

    private void down(Integer units) {
        aim = aim + units;
    }

    private void forward(Integer units) {
        horizPos = horizPos + units;
        depthPos = depthPos + aim * units;
    }

    public Integer getTotal() {
        return horizPos * depthPos;
    }

    private void readAndExecuteInstruction(String instruction) {
        String instructionSplit[] = instruction.split(" ");
        String actualInstruction = instructionSplit[0];
        Integer units = Integer.parseInt(instructionSplit[1]);

        switch (actualInstruction) {
            case "up" :
                up(units);
                break;
            case "down" :
                down(units);
                break;
            case "forward" :
                forward(units);
                break;
        }
    }

    public void readInstructionsFromFile(String fileName) {
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String instruction = scanner.nextLine();
                readAndExecuteInstruction(instruction);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

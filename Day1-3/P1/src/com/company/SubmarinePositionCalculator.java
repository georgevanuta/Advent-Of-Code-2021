package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SubmarinePositionCalculator {
    private Integer horizPos;
    private Integer depthPos;

    public SubmarinePositionCalculator() {
        horizPos = 0;
        depthPos = 0;
    }

    public Integer getPosition() {
        return horizPos * depthPos;
    }

    private void increaseHoriz(Integer distance) {
        horizPos = horizPos + distance;
    }

    private void goUp(Integer distance) {
        depthPos = depthPos - distance;
    }

    private void goDown(Integer distance) {
        depthPos = depthPos + distance;
    }

    private void readAndExecuteInstruction(String instruction) {
        String instructionSplit[] = instruction.split(" ");
        String actualInstruction = instructionSplit[0];
        Integer distance = Integer.parseInt(instructionSplit[1]);
        switch (actualInstruction) {
            case "forward" :
                increaseHoriz(distance);
                break;
            case "up" :
                goUp(distance);
                break;
            case "down" :
                goDown(distance);
                break;
        }
    }

    public void readInstructions(String fileName) {
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String instruction = scanner.nextLine();
                this.readAndExecuteInstruction(instruction);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

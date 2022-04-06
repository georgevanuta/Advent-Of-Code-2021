package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EnergyMatrix {
    private ArrayList<ArrayList<Energy>> matrix;
    private Integer nrOfFlashes;

    /* Constructor */
    public EnergyMatrix() {
        matrix = new ArrayList<ArrayList<Energy>>();
        nrOfFlashes = 0;
    }

    /* Getter to make things more readable */
    private Energy getPoint(Integer i, Integer j) {
        return matrix.get(i).get(j);
    }

    /* For debugging */
    public void print() {
        for (int i = 0; i < matrix.size(); i++) {
            System.out.println();
            for (int j = 0; j < matrix.get(0).size(); j++) {
                System.out.print(getPoint(i, j).getLevel() + " ");
            }
        }
        System.out.println();
    }
    /* --- */

    /* For reading from file */
    private void addLine(ArrayList<Energy> line) {
        matrix.add(line);
    }

    private ArrayList<Energy> integerLineToEnergyLine(ArrayList<Integer> integers) {
        ArrayList<Energy> energies = new ArrayList<Energy>();
        for (int i = 0; i < integers.size(); i++) {
            energies.add(new Energy(integers.get(i)));
        }
        return energies;
    }
    /* --- */

    /* Reads the matrix from input file */
    public void readFromFile(String fileName) {
        File file = new File(fileName);

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
               List<String> strings = Arrays.asList(scanner.nextLine().split(""));
               ArrayList<Integer> integers = (ArrayList<Integer>) strings.stream().map(Integer::parseInt).collect(Collectors.toList());
               ArrayList<Energy> energies = integerLineToEnergyLine(integers);
               addLine(energies);
            }

            System.out.println("Done reading!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* For readability */
    private void incrementPoint(Integer i, Integer j) {
        matrix.get(i).get(j).incrementLevel();
    }

    private void resetPoint(Integer i, Integer j) {
        matrix.get(i).get(j).resetLevelAndStartFlashing();
    }

    private void stopFlashing(Integer i, Integer j) {
        matrix.get(i).get(j).stopFlashing();
    }

    private Boolean isOutOfBounds(Integer i, Integer j) {
        if (i >= matrix.size() || j >= matrix.get(0).size()) return true;
        if (i < 0 || j < 0) return true;
        return false;
    }

    private Boolean isFlashing(Integer i, Integer j) {
        return matrix.get(i).get(j).isFlashing();
    }
    /* --- */

    /* Seems like flood fill */
    private void increaseEnergyOfPoint(Integer i, Integer j) {
        if (isOutOfBounds(i, j)) return;

        if (!getPoint(i,j).getLevel().equals(9)) {
            if (!isFlashing(i, j))
                incrementPoint(i, j);
            return;
        }

        nrOfFlashes++;
        resetPoint(i, j);

        increaseEnergyOfPoint(i-1, j);          // N
        increaseEnergyOfPoint(i+1, j);          // S
        increaseEnergyOfPoint(i, j-1);          // W
        increaseEnergyOfPoint(i, j+1);          // E
        increaseEnergyOfPoint(i-1, j-1);        // N-W
        increaseEnergyOfPoint(i-1, j+1);        // N-E
        increaseEnergyOfPoint(i+1, j-1);        // S-W
        increaseEnergyOfPoint(i+1, j+1);        // S-E
    }

    /* All points stop flashing */
    private void stopAllPointsFromFlashing() {
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(0).size(); j++) {
                stopFlashing(i, j);
            }
        }
    }

    /* Calls the increase method on all points */
    private void takeAStep() {
        stopAllPointsFromFlashing();
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(0).size(); j++) {
                increaseEnergyOfPoint(i, j);
            }
        }
    }

    /* ---PART 1--- */
    /* Takes a step 100 times */
    public void takeALeap() {
        Integer nrOfStepsPart1 = 100;
        for (int i = 0; i < nrOfStepsPart1; i++) {
            takeAStep();
        }
    }

    /* Print the result for Part 1 */
    public void printResultPart1() {
        System.out.println("After 100 steps there were " + nrOfFlashes + " flashes.");
    }
    /* ------ */

    /* ---PART 2--- */
    /* Returns true if all points are flashing */
    private Boolean areAllPointsFlashing() {
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size(); j++) {
                if (!isFlashing(i, j)) return false;
            }
        }
        return true;
    }

    public void printHowManyStepsTillSynchronizedFlashing() {
        Integer steps = 0;
        while (!areAllPointsFlashing()) {
            takeAStep();
            steps++;
        }
        System.out.println("It took " + steps + " steps till synchronized flashing.");
    }
    /* ------ */
}

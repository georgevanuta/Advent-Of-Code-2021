package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class SmokeMatrix {
    /* Actual Matrix */
    private ArrayList<ArrayList<Integer>> matrix;
    private Integer currentSizeOfBasin;
    private ArrayList<Integer> allSizesOfBasins;

    /* Constructor */
    public SmokeMatrix() {
        matrix = new ArrayList<ArrayList<Integer>>();
        currentSizeOfBasin = 0;
        allSizesOfBasins = new ArrayList<Integer>();
    }

    /* Returns size of current basin */
    public Integer getSizeOfBasin() {
        return currentSizeOfBasin;
    }

    /* Getter to make things more readable */
    private Integer getPoint(Integer i, Integer j) {
        if (i >= matrix.size() || j >= matrix.get(0).size()) {
            System.out.println("Point outside the matrix!");
            return -1;
        }

        return matrix.get(i).get(j);
    }

    /* Setter, same purpose as the getter */
    private void setPoint(Integer value, Integer i, Integer j) {
        if (i >= matrix.size() || j >= matrix.get(0).size()) {
            System.out.println("Point outside the matrix!");
            return;
        }

        matrix.get(i).set(j, value);
    }


    /* For debugging */
    public void print() {
        for (int i = 0; i < matrix.size(); i++) {
            System.out.println();
            for (int j = 0; j < matrix.get(0).size(); j++) {
                if (j == 0)
                    if (getPoint(i, j) < 0) System.out.print(getPoint(i, j));
                    else System.out.print(" " + getPoint(i, j));
                else if (getPoint(i, j) < 0) System.out.print(" " + getPoint(i, j));
                else System.out.print("  " + getPoint(i, j));
            }
        }
    }

    public void printSizesOfBasins() {
        for (int i = 0; i < allSizesOfBasins.size(); i++) {
            System.out.print(allSizesOfBasins.get(i) + " ");
        }
    }
    /* --- */

    /* Adds a whole line to the matrix */
    private void addLine(ArrayList<Integer> line) {
        matrix.add(line);
    }

    /* For reading from file */
    private ArrayList<Integer> stringToList(String string) {
        List<String> strings = Arrays.asList(string.split(""));
        ArrayList<Integer> integers = new ArrayList<Integer>(); // to be returned
        /* java8 magic */
        integers.addAll(strings.stream().map(Integer::parseInt).collect(Collectors.toList()));
        return integers;
    }

    /* Read from input file */
    public void readMatrixFromFile(String fileName) {
        File file = new File(fileName);

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                addLine(stringToList(scanner.nextLine()));
            }

            System.out.println("Done reading from file!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* Marks a point that has been added to a basin */
    private void markPoint(Integer i, Integer j) {
        matrix.get(i).set(j, -1);   // -1 means the point has been visited
    }

    /* Checks if a point has been iterated trough */
    private Boolean isPointMarkedDown(Integer i, Integer j) {
        if (getPoint(i, j) < 0) return true;
        return false;
    }

    /* Checks if a point is 9 */
    private Boolean isPointAWall(Integer i, Integer j) {
        if (getPoint(i, j).equals(9)) return true;
        return false;
    }

    private Boolean isPointOutOfBounds(Integer i, Integer j) {
        if (i < 0 || j < 0) return true;
        if (i >= matrix.size() || j >= matrix.get(0).size()) return true;
        return false;
    }

    /* Stores at currentSizeOfBasin the size of the basin located
    * at (i,j) before calling the function make sure currentSizeOfBasin
    * is 0 */
    public void calculateSizeOfBasin(Integer i, Integer j) {
        /* Check for out of bounds */
        if (isPointOutOfBounds(i, j)) return;

        /* Check for wall or point already marked down */
        if (isPointAWall(i, j) || isPointMarkedDown(i, j)) return;

        markPoint(i, j);    // Mark current point as visited
        currentSizeOfBasin++;      // Increase the size

        calculateSizeOfBasin(i-1, j);   // Up
        calculateSizeOfBasin(i+1, j);   // Down
        calculateSizeOfBasin(i, j-1);   // Left
        calculateSizeOfBasin(i, j+1);   // Right
    }

    /* Stores currentSizeOfBasin then sets it to 0 */
    private void resetAndStoreSizeOfBasin() {
        allSizesOfBasins.add(currentSizeOfBasin);
        currentSizeOfBasin = 0;
    }

    /* Go to the next basin, mark it, then store it's size */
    public Boolean goToNextBasin() {
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(0).size(); j++) {
                if (!isPointMarkedDown(i, j) && !isPointAWall(i, j)) {
                    calculateSizeOfBasin(i, j);
                    resetAndStoreSizeOfBasin();
                    return true;    // returns true if it has not reached the end of the basins
                }
            }
        }
        return false;
    }

    /* Iterates through all basins, marks them down, and stores their sizes */
    public void visitAllBasins() {
        Boolean condition = true;
        while (condition) {
            condition = goToNextBasin();
        }
    }

    /* Print the solution */
    public void printProductOfThreeLargestBasins() {
        Collections.sort(allSizesOfBasins);
        Integer product = 1;
        Integer sizeOfList = allSizesOfBasins.size();
        for (int i = 1; i <= 3; i++) {
            product = product * allSizesOfBasins.get(sizeOfList - i);
        }
        System.out.println("The solution is " + product);
    }

}

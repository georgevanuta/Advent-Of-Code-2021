package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SmokeMatrix {
    List<List<Integer>> matrix;

    /* Constructor */
    public SmokeMatrix() {
        matrix = new ArrayList<List<Integer>>();
    }

    /* Private getter to make things easier */
    private Integer getPoint(Integer i, Integer j) {
        return matrix.get(i).get(j);
    }

    /* Initialize matrix from file */
    public void readMatrixFromFile(String fileName) {
        File file = new File(fileName);

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                List<String> strings = Arrays.asList(scanner.nextLine().split(""));
                ArrayList<Integer> heights = new ArrayList<Integer>();

                for (int i = 0; i < strings.size(); i++) {
                    heights.add(Integer.parseInt(strings.get(i)));
                }

                matrix.add(heights);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* For debugging */
    public void printMatrix() {
        Integer n = matrix.size();
        Integer m = matrix.get(0).size();
        for (int i = 0; i < n; i++) {
            System.out.println();
            for (int j = 0; j < m; j++) {
                System.out.print(matrix.get(i).get(j) + " ");
            }
        }
    }
    /* --- */

    /* Returns a list of the up-down, left-right neighbors of a point */
    private ArrayList<Integer> nearPoints(Integer i, Integer j) {
        ArrayList<Integer> neighbors = new ArrayList<Integer>();

        if (i != 0) neighbors.add(getPoint(i - 1, j));                          /* up    */
        if (i != matrix.size() - 1) neighbors.add(getPoint(i + 1, j));          /* down  */
        if (j != 0) neighbors.add(getPoint(i, j - 1));                          /* left  */
        if (j != matrix.get(0).size() - 1) neighbors.add(getPoint(i, j + 1));   /* right */

        return neighbors;
    }

    /* Returns true if it's lower than it's neighbors */
    private Boolean isPointLowest(Integer i, Integer j) {
        if (i >= matrix.size() || j >= matrix.get(0).size()) {
            System.out.println("Invalid coordinates!");
            return false;
        }

        ArrayList<Integer> neighbors = nearPoints(i, j);

        for (Integer neighbor : neighbors) {
            if (getPoint(i, j) >= neighbor) return false;
        }

        return true;
    }

    /* Sum of all (local minimums + 1) */
    public Integer getSumOfLowestPoints() {
        Integer sum = 0;

        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(0).size(); j++) {
                if (isPointLowest(i, j)) sum = sum + getPoint(i, j) + 1;
            }
        }

        return sum;
    }
}

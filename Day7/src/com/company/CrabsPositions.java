package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CrabsPositions {
    ArrayList<Integer> positions;

    /* Constructor */
    public CrabsPositions() {
        positions = new ArrayList<Integer>();
    }

    /* Read positions from File */
    public void initializeFromFile(String fileName) {
        File file = new File(fileName);

        try {
            Scanner scanner = new Scanner(file);

            List<String> crabsStringList = Arrays.asList(scanner.nextLine().split(","));

            for (String s : crabsStringList) {
                positions.add(Integer.parseInt(s));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* ---PART1--- */
    private Integer getIdealPosition() {
        /* The closest point in 1D to all other points is the one
        * who has the same number of points smaller than it and bigger than it*/
        Collections.sort(positions);
        return positions.get(positions.size()/2);
    }

    public Integer getIdealFuel() {
        Integer fuel = 0;
        Integer idealPosition = getIdealPosition();

        for (Integer position : positions) {
            fuel = fuel + absoluteValue(position - idealPosition);
        }

        return fuel;
    }
    /* --- */

    /* Returns the absolute value of a number */
    private Integer absoluteValue(Integer integer) {
        if (integer > 0) return integer;
        return -integer;
    }

    /* Returns the sum of the first n natural number */
    public Integer gaussSum(Integer n) {
        return n * (n + 1) / 2;
    }

    /* ---PART 2--- */
    private Integer getCorrectFuelConsumption(Integer positionIndex) {
        if (positionIndex >= positions.size()) {
            System.out.println("Invalid Index!");
            return -1;
        }

        Integer fuel = 0;
        Integer desiredPosition = positions.get(positionIndex);
        for (int i = 0; i < positions.size(); i++) {
            fuel = fuel + gaussSum(absoluteValue(desiredPosition - positions.get(i)));
        }

        return fuel;
    }

    public Integer getCorrectMinimumFuelConsumption() {
        Integer minimumFuel = getCorrectFuelConsumption(0);

        for (int i = 0; i < positions.size(); i++) {
            Integer currentFuel = getCorrectFuelConsumption(i);
            if (minimumFuel > currentFuel) {
                minimumFuel = currentFuel;
            }
        }

        return minimumFuel;
    }
}

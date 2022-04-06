package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SchoolOfFish {
    private ArrayList<Long> reproductionCycle;

    /* CONSTANTS */
    private final Integer TOTAL_REPRODUCTION_DAYS = 9;  /* maximum number of days */
    private final Integer NORMAL_REPRODUCTION_DAYS = 6; /* fish who have birth are added at this day */
    private final Integer BORN_REPRODUCTION_DAYS = 8;   /* fish who were born are added here */

    /* Constructor */
    public SchoolOfFish() {
        reproductionCycle = new ArrayList<Long>();

        /* Initialize the reproductionCycle with 0 */
        for (int i = 0; i < TOTAL_REPRODUCTION_DAYS; i++) {
            reproductionCycle.add(0L);
        }
    }

    /* For debugging */
    public void print() {
        for (int i = 0; i < TOTAL_REPRODUCTION_DAYS - 1; i++) {
            System.out.print(reproductionCycle.get(i) + ",");
        }
        System.out.print(reproductionCycle.get(TOTAL_REPRODUCTION_DAYS - 1) + "\n");
    }
    /* --- */

    /* Adds fish based on its number of Days till reproduction */
    private void addAFish(Integer nrOfDays) {
        if (nrOfDays >= TOTAL_REPRODUCTION_DAYS) {
            System.out.println("Too many days!");
            return;
        }
        Long currentNrOfFish = reproductionCycle.get(nrOfDays);
        reproductionCycle.set(nrOfDays, currentNrOfFish + 1);
    }

    /* Initialize reproductionCycle from input file */
    public void initializeFromFile(String fileName) {
        File file = new File(fileName);

        try {
            Scanner scanner = new Scanner(file);

            List<String> fishStringList = Arrays.asList(scanner.nextLine().split(","));

            for (String s : fishStringList) {
                addAFish(Integer.parseInt(s));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* Simulates one day of reproduction */
    private void oneDaysGoesBy() {
        Long bornToday = reproductionCycle.get(0);  /* At the first position are the fish born today */

        /* Rotate the sublist of the fish who weren't just given birth to */
        Collections.rotate(reproductionCycle.subList(0, BORN_REPRODUCTION_DAYS - 1), -1);

        /* Simulate a rotation on the last three positions */
        Long aux7thDay = reproductionCycle.get(BORN_REPRODUCTION_DAYS - 1);
        reproductionCycle.set(BORN_REPRODUCTION_DAYS - 1, reproductionCycle.get(BORN_REPRODUCTION_DAYS));
        reproductionCycle.set(BORN_REPRODUCTION_DAYS, 0L);

        /* What was yesterday on day 7 will be added today to dat 6 */
        reproductionCycle.set(BORN_REPRODUCTION_DAYS, reproductionCycle.get(BORN_REPRODUCTION_DAYS) + bornToday);
        reproductionCycle.set(NORMAL_REPRODUCTION_DAYS, reproductionCycle.get(NORMAL_REPRODUCTION_DAYS) + aux7thDay);
    }

    /* Simulates n days of reproduction */
    public void letNDaysGoBy(Integer n) {
        for (int i = 0; i < n; i++) {
            this.oneDaysGoesBy();
        }
    }

    /* Prints how many fish are alive */
    public void printNumberOfFish() {
        Long nr = 0L;
        for (int i = 0; i < TOTAL_REPRODUCTION_DAYS; i++) {
            nr = nr + reproductionCycle.get(i);
        }
        System.out.println(nr);
    }
}

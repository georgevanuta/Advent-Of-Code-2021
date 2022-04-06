package com.company.polymer;

import com.company.minmax.MinMaxMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/* This class applies
* the pair rules */
public class Polymer {
    private ArrayList<Character> template;
    private RuleMap ruleMap;

    /* Constructor */
    public Polymer() {
        template = new ArrayList<Character>();
        ruleMap = new RuleMap();
    }

    /* Initialize Polymer from file */
    public void readFromFile(String fileName) {
        File file = new File(fileName);

        try {
            Scanner scanner = new Scanner(file);
            /* Template line */
            template =  new ArrayList<Character>(Arrays.asList(scanner.nextLine().chars().
                    mapToObj(c -> (char)c).toArray(Character[]::new)));

            /* Empty line */
            scanner.nextLine();

            /* Add rules */
            while (scanner.hasNextLine()) {
                String lineSplit[] = scanner.nextLine().split(" -> ");
                ruleMap.addRule(lineSplit[0], lineSplit[1].charAt(0));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* Returns the nth pair */
    /* There are (template.size() - 1) many pairs */
    public String getNthPair(Integer n) {
        return "" + template.get(n) + template.get(n+1);
    }

    /* Inserts character between the nth pair
    * The new character's position is n+1 */
    public void insertBetweenNthPair(Integer n, Character c) {
        template.add(n+1, c);
    }

    /* ---For debugging--- */
    public void printTemplate() {
        System.out.println();
        for (int i = 0; i < template.size(); i++) {
            System.out.print(template.get(i));
        }
        System.out.println();
    }
    /* --- */

    /* Iterates through all the pairs and inserts when
    * pair is found */
    public void takeAStep() {
        for (int i = 0; i < template.size() - 1; i++) {
            Character charToBeInserted = ruleMap.get(getNthPair(i));
            if (charToBeInserted != null) {
                insertBetweenNthPair(i, charToBeInserted);
                i++;
            }
        }
    }

    /* ---PART 1--- */
    /* Does the same things as above but 10 times */
    public void take10Steps() {
        for (int i = 0; i < 10; i++) {
            takeAStep();
        }
    }
    /* --- */

    /* ---PART 2--- */
    /* Does the same thing as above but 40 times */
    public void take40steps() {
        for (int i = 0; i < 40; i++) {
            System.out.println("---Step " + (i+1) + " loading---");
            takeAStep();
        }
    }
    /* --- */

    /* Prints the result for ---PART 1--- */
    public void printResult() {
        MinMaxMap minMaxMap = new MinMaxMap(template);
        minMaxMap.calculateAppearances();
        minMaxMap.initMinMax();
        minMaxMap.printResult();
    }
}

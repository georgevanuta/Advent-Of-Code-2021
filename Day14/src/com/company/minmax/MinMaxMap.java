package com.company.minmax;

import java.util.ArrayList;
import java.util.HashMap;

/* This class handles the number of appearances of
* the less/most frequent Character
* in an ArrayList */
public class MinMaxMap {
    private HashMap<Character, Long> map;  /* stores the number of appearances of each Character */
    private ArrayList<Character> list;      /* the list which will be iterated through */
    private Long min, max;

    /* Constructor */
    public MinMaxMap(ArrayList<Character> listChars) {
        map = new HashMap<Character, Long>();
        list = listChars;
        min = Long.MAX_VALUE;
        max = -1l;
    }

    /* Stores in the map the number of appearances
    * of each Character */
    public void calculateAppearances() {
        for (Character character : list) {
            if (map.containsKey(character)) {
                map.replace(character, map.get(character)+1);   /* increase the counter */
            } else {
                map.put(character, 0l);  /* start the counter */
            }
        }
    }

    /* Initializes the min/max values */
    public void initMinMax() {
        for (Long appearances : map.values()) {
            if (appearances > max) max = appearances;
            if (appearances < min) min = appearances;
        }
    }

    /* Prints the result */
    public void printResult() {
        System.out.println(max - min);
    }
}

package com.company.polymer;

import java.util.HashMap;

/* This class stores the rules */
public class RuleMap {
    /* Store the pair inserion
    * rules in a map */
    private HashMap<String, Character> map;

    /* Constructor */
    public RuleMap() {
        map = new HashMap<String, Character>();
    }

    /* Getter from map */
    public Character get(String key) {
        return map.get(key);
    }


    /* Adds a rule */
    public void addRule(String s, Character c) {
        map.put(s, c);
    }
}

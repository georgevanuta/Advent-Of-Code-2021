package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class SyntaxChecker {
    /* The lines of the subsystem*/
    private ArrayList<String> lines;
    private Integer scorePart1;
    private Integer scorePart2;
    private ArrayList<Long> scoresEachLinePart2;

    /* Constructor */
    public SyntaxChecker() {
        lines = new ArrayList<String>();
        scorePart1 = 0;
        scorePart2 = 0;
        scoresEachLinePart2 = new ArrayList<Long>();
    }

    /* For debugging */
    public void print() {
        for (int i = 0; i < lines.size(); i++) {
            System.out.println(lines.get(i));
        }
    }

    /* Reads the input from file */
    public void readLinesFromFile(String fileName) {
        File file = new File(fileName);

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }

            System.out.println("Done reading from file!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* Checks if a parentheses is open */
    private Boolean isOpen(Character parentheses) {
        if (parentheses.equals('{') || parentheses.equals('[') || parentheses.equals('(') || parentheses.equals('<'))
            return true;
        return false;
    }

    /* Checks if two parentheses are coupled*/
    private Boolean checkIfParenthesesAreCoupled(Character open, Character close) {
        if (open.equals('{') && close.equals('}')) return true;
        if (open.equals('[') && close.equals(']')) return true;
        if (open.equals('(') && close.equals(')')) return true;
        if (open.equals('<') && close.equals('>')) return true;

        return false;
    }

    /* ---PART 1---*/
    /* Score of one syntax error */
    private Integer getIllegalScore(Character parentheses) {
        switch (parentheses) {
            case ']':
                return 57;
            case ')':
                return 3;
            case '}':
                return 1197;
            case '>':
                return 25137;
            default:
                System.out.println("Not valid!");
                return -1;
        }
    }

    /* Score for first illegal parentheses */
    private void scoreIfLineIsInvalid(Integer whichLine) {
        Stack<Character> stack = new Stack<Character>();
        String line = lines.get(whichLine);
        for (int i = 0; i < line.length(); i++) {
            if (isOpen(line.charAt(i))) {
                stack.push(line.charAt(i));
            } else {
                if (stack.empty()) return;
                Character parentheses = stack.pop();
                if (!checkIfParenthesesAreCoupled(parentheses, line.charAt(i))) {
                    scorePart1 = scorePart1 + getIllegalScore(line.charAt(i));
                    return;
                }
            }
        }
        return;
    }

    /* Iterate through all lines */
    public void calculateIllegalScore() {
        for (int i = 0; i < lines.size(); i++) {
            scoreIfLineIsInvalid(i);
        }
    }

    /* Prints the score for part 1 */
    public void printScorePart1() {
        System.out.println("The score for part 1 is " + scorePart1);
    }
    /* ---END PART 1--- */

    /* ---PART 2--- */
    /* Returns incomplete score of a parentheses */
    public Integer getIncompleteScore(Character parentheses) {
        switch (parentheses) {
            case '(':
                return 1;
            case '[':
                return 2;
            case '{':
                return 3;
            case '<':
                return 4;
            default:
                System.out.println("Invalid!");
                return -1;
        }
    }

    /* Returns incomplete score of a stack */
    private Long getScoreFromStack(Stack<Character> stack) {
        if (stack == null) return 0l;
        if (stack.size() == 0) return 0l;

        Long partialScore = 0l;
        for (int i = stack.size() - 1; i >= 0; i--) {
            partialScore = partialScore * 5 + getIncompleteScore(stack.get(i));
        }
        return partialScore;
    }

    /* Returns the remained of the stack */
    private Stack<Character> returnStackIfLineIsIncomplete(Integer whichLine) {
        Stack<Character> stack = new Stack<Character>();
        String line = lines.get(whichLine);

        for (int i = 0; i < line.length(); i++) {
            if (isOpen(line.charAt(i))) {
                stack.push(line.charAt(i));
            } else {
                if (stack.empty()) return null;
                Character parentheses = stack.pop();
                if (!checkIfParenthesesAreCoupled(parentheses, line.charAt(i))) {
                    return null;
                }
            }
        }
        return stack;
    }

    /* Iterates through all lines */
    public void calculateIncompleteScore() {
        for (int i = 0; i < lines.size(); i++) {
            Long score = getScoreFromStack(returnStackIfLineIsIncomplete(i));
            if (score != 0)
                scoresEachLinePart2.add(score);
        }
    }

    /* For debugging */
    public void printAllScoresPart2() {
        for (int i = 0; i < scoresEachLinePart2.size(); i++) {
            System.out.print(scoresEachLinePart2.get(i) + " ");
        }
    }
    /* --- */

    /* Prints the score for Part 2 */
    public void printScorePart2() {
        Collections.sort(scoresEachLinePart2);
        System.out.println(scoresEachLinePart2.get(scoresEachLinePart2.size()/2));
    }
    /* ---END PART 2--- */


}

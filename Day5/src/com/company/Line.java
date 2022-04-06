package com.company;

import java.util.Scanner;

public class Line {
    /* The points that determine the line */
    private Point p1;
    private Point p2;

    /* Constructor */
    public Line() {}

    /* Getters */
    public Point getFirstPoint() {
        return p1;
    }

    public Point getSecondPoint() {
        return p2;
    }

    /* Used to determine the new size of the matrix */
    public Integer getBiggestX() {
        if (p2.getX() > p1.getX()) return p2.getX();
        return p1.getX();
    }

    public Integer getBiggestY() {
        if (p2.getY() > p1.getY()) return p2.getY();
        return p1.getY();
    }

    /* The only way to initialize a Line */
    /* Look at any line in the input file */
    public void StringToLine(String s) {
        Scanner scanner = new Scanner(s);
        s = new String(s.replace(",", " "));
        s = new String(s.replace(" -> ", " "));

        String sSplit[] = s.split(" ");

        Point p1 = new Point(Integer.parseInt(sSplit[0]), Integer.parseInt(sSplit[1]));
        Point p2 = new Point(Integer.parseInt(sSplit[2]), Integer.parseInt(sSplit[3]));
        this.p1 = p1;
        this.p2 = p2;
    }

    /* Used in PART 1 to check if this line is horizontal or vertical */
    public Boolean checkIfStraight() {
        if (p1.getX().equals(p2.getX())) {
            return true;
        }
        if (p1.getY().equals(p2.getY())) {
            return true;
        }
        return false;
    }

    /* For debugging */
    @Override
    public String toString() {
        return p1 + " -> " + p2;
    }
}

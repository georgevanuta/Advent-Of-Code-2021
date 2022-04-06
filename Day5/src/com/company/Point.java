package com.company;

public class Point {
    /* The coordinates of a point */
    private final Integer x;
    private final Integer y;

    /* Constructor */
    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    /* Getters */
    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    /* For debugging */
    @Override
    public String toString() {
        return x + "," + y;
    }
}

package com.company.paper;

import java.util.Objects;

/* This class represents a marked point
* on the paper */
public class Point {
    /* Coordinates */
    private Integer x;
    private Integer y;

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
    /* --- */

    /* For debugging */
    public String toString() {
        return "[" + x + "," + y + "]";
    }

    /* Because I'm working with sets */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(x, point.x) && Objects.equals(y, point.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    /* --- */
}
package com.company.paper;

import java.util.Iterator;

/* ---PART 2--- */
/* This class takes the remaining
* marked points and prints them in a
* matrix */
public class PaperVisualizer {
    /* Stores the output matrix */
    private Character matrix[][];
    private Integer n;
    private Integer m;

    /* Constructor */
    public PaperVisualizer(Paper paper) {
        setSizes(paper);
        makeMatrixEmpty();
        markPoints(paper);
    }

    /* For readability */
    private void markPoint(Point point) {
        matrix[point.getY()][point.getX()] = '#';
    }

    private Character getPoint(Integer x, Integer y) {
        return matrix[y][x];
    }
    /* --- */

    /* Gets the matrix size from paper and initializes
    * the matrix */
    private void setSizes(Paper paper) {
        this.n = paper.getBiggestY() + 1;
        this.m = paper.getBiggestX() + 1;
        matrix = new Character[n][m];
    }

    /* Makes the matrix full of dots */
    private void makeMatrixEmpty() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = '.';
            }
        }
    }

    /* Marks the points on the matrix */
    private void markPoints(Paper paper) {
        Iterator<Point> iterator = paper.getMarkedPoints().iterator();
        while (iterator.hasNext()) {
            markPoint(iterator.next());
        }
    }

    /* Prints the result */
    public void printMatrix() {
        for (int i = 0; i < n; i++) {
            System.out.println();
            for (int j = 0; j < m; j++) {
                System.out.print(getPoint(j, i) + " ");
            }
        }
    }
}

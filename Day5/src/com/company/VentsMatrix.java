package com.company;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VentsMatrix {
    private List<List<String>> matrix;  /* The actual matrix */
    private Integer n;                  /* Number of lines */
    private Integer m;                  /* Number of rows */

    /* Constructor */
    public VentsMatrix() {
        matrix = new ArrayList<List<String>>();
        n = 1;
        m = 1;
        matrix.add(new ArrayList<String>());
        matrix.get(0).add(".");
    }

    /* private setters and getters for a point in the matrix */
    private String getPoint(Point p) {
        return matrix.get(p.getY()).get(p.getX());
    }

    private void setPoint(Point p, String s) {
        matrix.get(p.getY()).set(p.getX(), s);
    }

    /* For debugging */
    public void print() {
        for (int i = 0; i < n; i++) {
            System.out.println();
            for (int j = 0; j < m; j++) {
                System.out.print(matrix.get(i).get(j) + " ");
            }
        }
    }
    /* --- */

    /* Makes a line with M '.' */
    /* Used in addLines */
    public List<String> lineWithMDots() {
        List<String> line = new ArrayList<String>();
        for (int i = 0; i < m; i++) {
            line.add(".");
        }
        return line;
    }

    /* Helpers for redimMatrix */
    private void addRows(Integer newM) {
        if (newM > m) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < newM - m; j++) {
                    matrix.get(i).add(".");
                }
            }
            m = newM;
        }
    }


    public void addLines(Integer newN) {
        if (newN > n) {
            for (int i = 0; i < newN - n; i++) {
                matrix.add(lineWithMDots());
            }
            n = newN;
        }
    }

    /* In order to have a dynamic matrix */
    private void redimMatrix(Integer n, Integer m) {
        addRows(m+1);
        addLines(n+1);
    }

    private void updatePoint(Point p) {
        /* For debugging */
        if (p.getX() >= m || p.getY() >= n) {
            System.out.println("Can't update!");
            return;
        }
        /* --- */

        if (getPoint(p).equals(".")) {  /* If a point is not crossed by a line */
            setPoint(p, "1");        /* set it to 1*/
            return;
        }
        Integer updated = Integer.parseInt(getPoint(p)) + 1;    /* else increment it's value */
        setPoint(p, updated.toString());
    }

    /* Given a line, draws it in the matrix */
    private void drawLine(Line line) {
        /* In case the point is outside the current matrix */
        redimMatrix(line.getBiggestY(), line.getBiggestX());

        /* currentX/Y goes to final X/Y in order to draw the line */
        Integer currentX = line.getFirstPoint().getX();
        Integer currentY = line.getFirstPoint().getY();

        Integer finalX = line.getSecondPoint().getX();
        Integer finalY = line.getSecondPoint().getY();

        /* loops trough the points */
        while (!currentX.equals(finalX) || !currentY.equals(finalY)) {
            updatePoint(new Point(currentX, currentY));

            if (currentX > finalX) currentX--;
            if (currentX < finalX) currentX++;

            if (currentY > finalY) currentY--;
            if (currentY < finalY) currentY++;

        }

        /* the loop stops at the last point */
        updatePoint(new Point(finalX, finalY));
    }

    /* ---PART 1--- */
    public void readFromFileStraightLines(String fileName) {
        File file = new File(fileName);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                Line line = new Line();
                line.StringToLine(s);
                if (line.checkIfStraight()) {
                    this.drawLine(line);
                }
            }
            System.out.println("Done reading.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* ---PART 2--- */
    public void readAllLinesFromFile(String fileName) {
        File file = new File(fileName);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                Line line = new Line();
                line.StringToLine(s);
                this.drawLine(line);
            }
            System.out.println("Done reading.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* After reading from the file and creating the matrix */
    public Integer getPointsCrossedAtLeastNTimes(Integer n) {
        Integer nr = 0;

        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                if (matrix.get(i).get(j).equals(".")) continue;
                Integer number = Integer.parseInt(matrix.get(i).get(j));
                if (number >= n) nr++;
            }
        }
        return nr;
    }


}

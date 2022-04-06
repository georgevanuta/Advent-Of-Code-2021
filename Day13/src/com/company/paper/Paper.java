package com.company.paper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import com.company.folding.*;

/* This class holds the marked points
* and does fold-type operations on
* "the paper" */
public class Paper {
    /* Set of all the marked points */
    private Set<Point> markedPoints;    /* No duplicates */

    /* Constructor */
    public Paper() {
        markedPoints = new HashSet<Point>();
    }

    /* Getter */
    public Set<Point> getMarkedPoints() {
        return markedPoints;
    }

    /* Replaces the marked points with others */
    public void replaceMarkedPoints(Set<Point> newMarkedPoints) {
        this.markedPoints = newMarkedPoints;
    }

    /* Reads the marked points from input file */
    public void readPaperFromFileAndFold(String fileName) {
        File file = new File(fileName);

        try {
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            /* reads initial marked points */
            while (true) {
                if (line.isEmpty()) break;
                String lineSplit[] = line.split(",");
                Point point = new Point(Integer.parseInt(lineSplit[0]),
                        Integer.parseInt(lineSplit[1]));
                markedPoints.add(new Point(Integer.parseInt(lineSplit[0]),
                        Integer.parseInt(lineSplit[1])));
                line = scanner.nextLine();
            }

            /* reads fold instructions */
            FoldingContext foldingContext = new FoldingContext();
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                Integer byWhichValue = Integer.parseInt(line.split("=")[1]);
                if (line.contains("x")) {
                    foldingContext.setFoldingStrategy(new ConcreteStrategyFoldByX());
                } else {
                    foldingContext.setFoldingStrategy(new ConcreteStrategyFoldByY());
                }
                foldingContext.executeFoldingStrategy(this, byWhichValue);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* For debugging */
    public void printPaper() {
        Iterator<Point> iterator = markedPoints.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
    /* --- */

    /* Returns the biggest x from the marked points */
    public Integer getBiggestX() {
        Integer max = -1;
        Iterator<Point> iterator = markedPoints.iterator();
        while (iterator.hasNext()) {
            Integer currentX = iterator.next().getX();
            if (currentX > max) max = currentX;
        }
        return max;
    }

    /* Returns the biggest y from the marked points */
    public Integer getBiggestY() {
        Integer max = -1;
        Iterator<Point> iterator = markedPoints.iterator();
        while (iterator.hasNext()) {
            Integer currentY = iterator.next().getY();
            if (currentY > max) max = currentY;
        }
        return max;
    }

    /* Solves ---PART 1---*/
    public void printNumberOfMarkedPoints() {
        System.out.println("There are " + markedPoints.size() + " marked points.");
    }
}

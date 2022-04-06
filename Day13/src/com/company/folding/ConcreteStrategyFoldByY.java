package com.company.folding;

import com.company.paper.Paper;
import com.company.paper.Point;

import java.util.HashSet;
import java.util.Iterator;

public class ConcreteStrategyFoldByY implements FoldingStrategy{
    public void fold(Paper paper, Integer y) {
        Iterator<Point> iterator = paper.getMarkedPoints().iterator();
        Integer length = paper.getMarkedPoints().size();
        HashSet<Point> newMarkedPoints = new HashSet<Point>();
        while (iterator.hasNext()) {
            Point point = iterator.next();
            if (point.getY().compareTo(y + 1) >= 0) {
                Point pointToBeAdded = new Point(point.getX(), 2 * y - point.getY());
                newMarkedPoints.add(pointToBeAdded);
                newMarkedPoints.remove(point);
            } else {
                newMarkedPoints.add(point);
            }
        }
        paper.replaceMarkedPoints(newMarkedPoints);
    }
}

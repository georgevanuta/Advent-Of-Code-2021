package com.company.folding;

import com.company.paper.Paper;
import com.company.paper.Point;

import java.util.HashSet;
import java.util.Iterator;

public class ConcreteStrategyFoldByX implements FoldingStrategy {
    public void fold(Paper paper, Integer x) {
        Iterator<Point> iterator = paper.getMarkedPoints().iterator();
        Integer length = paper.getMarkedPoints().size();
        HashSet<Point> newMarkedPoints = new HashSet<Point>();
        while (iterator.hasNext()) {
            Point point = iterator.next();
            if (point.getX().compareTo(x + 1) >= 0) {
                Point pointToBeAdded = new Point(2 * x - point.getX(), point.getY());
                newMarkedPoints.add(pointToBeAdded);
                newMarkedPoints.remove(point);
            } else {
                newMarkedPoints.add(point);
            }
        }
        paper.replaceMarkedPoints(newMarkedPoints);
    }
}

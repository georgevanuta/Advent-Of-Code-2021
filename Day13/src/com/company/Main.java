package com.company;

import com.company.paper.Paper;
import com.company.paper.PaperVisualizer;

public class Main {
    public static void main(String[] args) {
        Paper paper = new Paper();
        paper.readPaperFromFileAndFold("src/com/company/tests/input.txt");
        PaperVisualizer paperVisualizer = new PaperVisualizer(paper);
        paperVisualizer.printMatrix();
    }
}

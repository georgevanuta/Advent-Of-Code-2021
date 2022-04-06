package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BingoNumbers {
    private List<Integer> bingos;
    private Integer currentBingoIndex;

    public BingoNumbers() {
        bingos = new ArrayList<Integer>();
        currentBingoIndex = 0;
    }

    public Integer getNextBingo() {
        if (currentBingoIndex == bingos.size()) return null;
        currentBingoIndex++;
        return bingos.get(currentBingoIndex - 1);
    }

    public void readBingosFromFile(String fileName) {
        try {
            String fileContent = Files.readString(Path.of(fileName));
            bingos = Arrays.stream(fileContent.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printBingos() {
        System.out.println(bingos);
    }
}

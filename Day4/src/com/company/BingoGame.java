package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BingoGame {
    private BingoNumbers bingoNumbers;
    private ArrayList<Board> boards;
    final int dim = 5;

    public BingoGame() {
        bingoNumbers = new BingoNumbers();
        boards = new ArrayList<Board>();
    }

    public void initializeGame(String fileBingos, String fileBoards) {
        initializeBingoNumbers(fileBingos);
        initializeBoards(fileBoards);
    }

    private void initializeBingoNumbers(String fileName) {
        bingoNumbers.readBingosFromFile(fileName);
    }

    private void initializeBoards(String fileName) {
        File file = new File(fileName);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                Board board = new Board();
                for (int i = 0; i < dim; i++) {
                    for (int j = 0; j < dim; j++) {
                        board.addNumber(scanner.nextInt());
                    }
                }
                boards.add(board);
                scanner.nextLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void startGame() {   // part 1
        Integer currentBingoNumber = bingoNumbers.getNextBingo();
        Boolean didSomeoneWin = false;
        while (currentBingoNumber != null && didSomeoneWin != true) {
            for (int i = 0; i < boards.size(); i++) {
                boards.get(i).checkIfHasBingoNumber(currentBingoNumber);
                if (boards.get(i).checkIfBoardWon()) {
                    int score = boards.get(i).getBingoScore();
                    System.out.println("A winner has been chosen!");
                    System.out.println("Board number " + (i + 1) + " has won the bingo Game!");
                    System.out.println("And the score is " + score + "!");
                    didSomeoneWin = true;
                    break;
                }
            }
            currentBingoNumber = bingoNumbers.getNextBingo();
        }
    }

    public void startLoserGame() {  // part 2
        Integer currentBingoNumber = bingoNumbers.getNextBingo();
        while (boards.size() != 1 && currentBingoNumber != null) {
            for (int i = 0; i < boards.size(); i++) {
                boards.get(i).checkIfHasBingoNumber(currentBingoNumber);
                if (boards.get(i).checkIfBoardWon()) {
                    System.out.println("A boards has won!");
                    boards.remove(i);
                }
            }
            if (boards.size() == 1) continue;
            currentBingoNumber = bingoNumbers.getNextBingo();
        }
        Board loserBoard = boards.get(0);   // After removing all winning boards, only one remains
        while (currentBingoNumber != null) {
            loserBoard.checkIfHasBingoNumber(currentBingoNumber);
            if (loserBoard.checkIfBoardWon()) {
                int loserScore = loserBoard.getBingoScore();
                System.out.println("The loser board has finally won!");
                System.out.println("And its loser's score is " + loserScore + "!:(");
                break;
            }
            currentBingoNumber = bingoNumbers.getNextBingo();
        }
    }

    /* Used for debugging */
    public void printAllBoards() {
        for (Board board : boards) {
            board.printBoard();
            System.out.println();
        }
    }
}

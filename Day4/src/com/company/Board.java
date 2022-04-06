package com.company;

public class Board {
    private BoardNumber[][] numbers;
    private Integer lastX;
    private Integer lastY;
    final int dim = 5;
    /* Useful for the final sum */
    private Integer bingoRow;
    private Integer bingoLine;
    private Integer lastBingo;

    public Board() {
        numbers = new BoardNumber[dim][dim];
    }

    public void addNumber(Integer number) {
        if (lastX == null && lastY == null) {
            lastX = 0;
            lastY = 0;
            numbers[lastX][lastY] = new BoardNumber(number);
            return;
        } else {
            if (lastX == dim - 1 && lastY == dim - 1) {
                System.out.println("Board is full!");
                return;
            }
            if (lastY == 4) {
                lastY = 0;
                lastX++;
            } else {
                lastY++;
            }
            numbers[lastX][lastY] = new BoardNumber(number);
        }
    }

    public void checkIfHasBingoNumber(Integer bingoNumber) {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (numbers[i][j].Number() == bingoNumber) {
                    numbers[i][j].getBingoed();
                    lastBingo = bingoNumber;
                }
            }
        }
    }

    /* Bingo methods */
    private Boolean checkForLine(Integer line) {
        for (int j = 0; j < dim; j++) {
            if (!numbers[line][j].isBingo()) {
                return false;
            }
        }
        bingoLine = line;
        return true;
    }

    private Boolean checkForRow(Integer row) {
        for (int i = 0; i < dim; i++) {
            if (!numbers[i][row].isBingo()) {
                return false;
            }
        }
        bingoRow = row;
        return true;
    }

    public Boolean checkIfBoardWon() {
        /* First check for lines */
        for (int i = 0; i < dim; i++) {
            if (checkForLine(i) == true) {
                return true;
            }
        }

        /* Secondly check for rows */
        for (int i = 0; i < dim; i++) {
            if (checkForRow(i) == true) {
                return true;
            }
        }

        /* Not a winner yet */
        return false;
    }

    public Integer getBingoScore() {
        Integer score = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (!numbers[i][j].isBingo()) {
                    score = score + numbers[i][j].Number();
                }
            }
        }
        return score * lastBingo;

    }

    /* Used for debugging */
    public Integer getLastBingo() {
        return lastBingo;
    }

    public void printBoard() {
        for (int i = 0; i < dim; i++) {
            System.out.println();
            for (int j = 0; j < dim; j++) {
                System.out.print(numbers[i][j].Number() + " ");
            }
        }
    }

    public void printBingoBoard() {
        for (int i = 0; i < dim; i++) {
            System.out.println();
            for (int j = 0; j < dim; j++) {
                if (numbers[i][j].isBingo()) {
                    System.out.print(" true ");
                } else {
                    System.out.print("false ");
                }
            }
        }
    }
}

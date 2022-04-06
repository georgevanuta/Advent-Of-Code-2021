package com.company;

public class Main {

    public static void main(String[] args) {
        String fileBingos = "src/com/company/inputBingos.txt";
        String fileBoards = "src/com/company/inputBoards.txt";
        BingoGame bingoGame = new BingoGame();
        bingoGame.initializeGame(fileBingos, fileBoards);

        /* Don't run them at the same time, each modifies the board! */

        //bingoGame.startGame();     //-> run for Part 1
        bingoGame.startLoserGame();  //-> run for Part 2
    }
}

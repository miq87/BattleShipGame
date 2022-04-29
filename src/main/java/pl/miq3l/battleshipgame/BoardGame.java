package pl.miq3l.battleshipgame;

import java.util.Arrays;
import java.util.Random;

public class BoardGame {
    private final int[][] boardGame;
    private static BoardGame INSTANCE;
    private static final int BOARDSIZE = 10;

    public int[][] getBoardGame() {
        return boardGame;
    }

    public static BoardGame getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new BoardGame(BOARDSIZE);
        }
        return INSTANCE;
    }

    private BoardGame(int size) {
        this.boardGame = new int[size][size];
    }

    public int[][] resetBoardGame() {
        Arrays.stream(boardGame).forEach(a -> {
            Arrays.fill(a, 0);
        });
        return boardGame;
    }

    public int[][] generateShips() {
        int numberOfShips = 3;

        for(int i = 0; i < numberOfShips; i++) {
            int col = new Random().nextInt(BOARDSIZE);
            int row = new Random().nextInt(BOARDSIZE);
            System.out.println("row = " + row + ", col = " + col);
            this.boardGame[row][col] = StatusEnum.SHIP.getValue();
        }
        return boardGame;
    }

    public int[][] hit(int row, int col) {
        if(boardGame[row][col] == 1) {
            boardGame[row][col] = StatusEnum.HIT.getValue();
        }
        else if(boardGame[row][col] == 0) {
            boardGame[row][col] = StatusEnum.MISSED.getValue();
        }
        return boardGame;
    }
}

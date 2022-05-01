package pl.miq3l.battleshipgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BoardGame {
    private final int[][] boardGame;
    private static BoardGame INSTANCE;
    private static final int BOARDSIZE = 10;
    private final List<Ship> ships = new ArrayList<>();

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
        resetBoardGame();
        int numberOfShips = 3;

        generateSingleShip(5);
        numberOfShips--;
        for(int i = 0; i < numberOfShips; i++) {
            generateSingleShip(4);
        }
        return boardGame;
    }

    public boolean generateSingleShip(int shipSize) {
        Ship ship = new Ship();
        if(new Random().nextBoolean()) {
            int col = new Random().nextInt(BOARDSIZE / 2);
            int row = new Random().nextInt(BOARDSIZE);
            for(int y = 0; y < shipSize; y++) {
                if(this.boardGame[row][col+y] == StatusEnum.SEA.getValue())
                    ship.getCoordinatesList().add(new Coordinates(row, col+y));
            }
            return true;
        }
        int col = new Random().nextInt(BOARDSIZE);
        int row = new Random().nextInt(BOARDSIZE / 2);
        for(int y = 0; y < shipSize; y++) {
            if(this.boardGame[row+y][col] == StatusEnum.SEA.getValue())
                ship.getCoordinatesList().add(new Coordinates(row+y, col));
        }
        ships.add(ship);
        System.out.println(ship);
        return true;
    }

    public int[][] hit(int row, int col) {
        if(boardGame[row][col] == 1) {
            boardGame[row][col] = StatusEnum.HITTED.getValue();
        }
        else if(boardGame[row][col] == 0) {
            boardGame[row][col] = StatusEnum.MISSED.getValue();
        }
        return boardGame;
    }
}

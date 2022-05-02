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
        ships.clear();
        int numberOfShips = 3;

        while(numberOfShips > 2) {
            if(generateSingleShip(5))
                numberOfShips--;
        }
        while(numberOfShips > 0) {
            if(generateSingleShip(4))
                numberOfShips--;
        }
        return boardGame;
    }

    private void saveShipToBoardGame(Ship ship) {
        ship.getCoordinates().forEach(coordinate -> {
            this.boardGame[coordinate.getRow()][coordinate.getCol()]
                    = StatusEnum.SHIP.getValue();
        });
    }

    public boolean generateSingleShip(int shipSize) {
        Ship ship = new Ship();
        if(new Random().nextBoolean()) {    // HORIZONTAL SHIP
            int col = new Random().nextInt(BOARDSIZE / 2);
            int row = new Random().nextInt(BOARDSIZE);
            for(int i = 0; i < shipSize; i++) {
                try {
                    if(boardGame[row-1][col+i] == StatusEnum.SEA.getValue() &&
                            boardGame[row][col+i-1] == StatusEnum.SEA.getValue() &&
                            boardGame[row][col+i+1] == StatusEnum.SEA.getValue() &&
                            boardGame[row][col+i] == StatusEnum.SEA.getValue() &&
                            boardGame[row+1][col+i] == StatusEnum.SEA.getValue()) {
                        ship.getCoordinates().add(new Coordinates(row, col+i));
                    }
                    else return false;
                }
                catch (Exception e) {
                    return false;
                }
            }
        }
        else {  // VERTICAL SHIP
            int col = new Random().nextInt(BOARDSIZE);
            int row = new Random().nextInt(BOARDSIZE / 2);
            for(int i = 0; i < shipSize; i++) {
                try {
                    if(boardGame[row+i][col-1] == StatusEnum.SEA.getValue() &&
                            boardGame[row+i-1][col] == StatusEnum.SEA.getValue() &&
                            boardGame[row+i][col] == StatusEnum.SEA.getValue() &&
                            boardGame[row+i+1][col] == StatusEnum.SEA.getValue() &&
                            boardGame[row+i][col+1] == StatusEnum.SEA.getValue()) {
                        ship.getCoordinates().add(new Coordinates(row+i, col));
                    }
                    else return false;
                }
                catch (Exception e) {
                    return false;
                }
            }
        }
        ships.add(ship);
        saveShipToBoardGame(ship);
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

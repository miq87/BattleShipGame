package pl.miq3l.battleshipgame;

import pl.miq3l.battleshipgame.model.Coordinates;
import pl.miq3l.battleshipgame.model.Ship;
import pl.miq3l.battleshipgame.model.StatusEnum;

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

        while(numberOfShips == 3) {
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
                    for(int x = -1; x < 2; x++) {
                        for(int y = -1; y < 2; y++) {
                            if(boardGame[row+x][col+i+y] != StatusEnum.SEA.getValue())
                                return false;
                        }
                    }
                }
                catch (Exception e) {
                    return false;
                }
                ship.getCoordinates().add(new Coordinates(row, col+i));
            }
        }
        else {  // VERTICAL SHIP
            int col = new Random().nextInt(BOARDSIZE);
            int row = new Random().nextInt(BOARDSIZE / 2);
            for(int i = 0; i < shipSize; i++) {
                try {
                    for(int x = -1; x < 2; x++) {
                        for(int y = -1; y < 2; y++) {
                            if(boardGame[row+i+x][col+y] != StatusEnum.SEA.getValue())
                                return false;
                        }
                    }
                }
                catch (Exception e) {
                    return false;
                }
                ship.getCoordinates().add(new Coordinates(row+i, col));
            }
        }
        ships.add(ship);
        saveShipToBoardGame(ship);
        return true;
    }

    public int[][] hit(Coordinates coordinates) {
        int row = coordinates.getRow();
        int col = coordinates.getCol();
        if(boardGame[row][col] == 1) {
            boardGame[row][col] = StatusEnum.HITTED.getValue();
        }
        else if(boardGame[row][col] == 0) {
            boardGame[row][col] = StatusEnum.MISSED.getValue();
        }
        return boardGame;
    }
}

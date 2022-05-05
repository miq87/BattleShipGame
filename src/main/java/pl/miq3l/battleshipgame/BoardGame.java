package pl.miq3l.battleshipgame;

import pl.miq3l.battleshipgame.model.Coordinates;
import pl.miq3l.battleshipgame.model.Ship;
import pl.miq3l.battleshipgame.model.SquareStatus;

import java.util.*;

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
        ships.forEach(ship -> {
            System.out.println(ship);
        });
        return boardGame;
    }

    private void saveShipToBoardGame(Ship ship) {
        ship.getCoordinates().forEach(coordinate -> {
            this.boardGame[coordinate.getRow()][coordinate.getCol()]
                    = SquareStatus.SHIP.getValue();
        });
    }

    public boolean generateSingleShip(int shipSize) {
        List<Coordinates> coordinates = new ArrayList<>();
        if(new Random().nextBoolean()) {    // HORIZONTAL SHIP
            int col = new Random().nextInt(BOARDSIZE / 2);
            int row = new Random().nextInt(BOARDSIZE);
            for(int i = 0; i < shipSize; i++) {
                try {
                    for(int x = -1; x < 2; x++) {
                        for(int y = -1; y < 2; y++) {
                            if(boardGame[row+x][col+i+y] != SquareStatus.SEA.getValue())
                                return false;
                        }
                    }
                }
                catch (Exception e) {
                    return false;
                }
                coordinates.add(new Coordinates(row, col+i));
            }
        }
        else {  // VERTICAL SHIP
            int col = new Random().nextInt(BOARDSIZE);
            int row = new Random().nextInt(BOARDSIZE / 2);
            for(int i = 0; i < shipSize; i++) {
                try {
                    for(int x = -1; x < 2; x++) {
                        for(int y = -1; y < 2; y++) {
                            if(boardGame[row+i+x][col+y] != SquareStatus.SEA.getValue())
                                return false;
                        }
                    }
                }
                catch (Exception e) {
                    return false;
                }
                coordinates.add(new Coordinates(row+i, col));
            }
        }
        String shipType = (shipSize == 5 ? "Battleship" : "Destroyers");
        Ship ship = new Ship(coordinates, shipSize, shipType);
        ships.add(ship);
        saveShipToBoardGame(ship);
        return true;
    }

    public int[][] hit(Coordinates coordinates) {
        int row = coordinates.getRow();
        int col = coordinates.getCol();
        if(boardGame[row][col] == SquareStatus.SHIP.getValue()) {
            boardGame[row][col] = SquareStatus.HITTED.getValue();
            reduceShipSize(coordinates);
        }
        else if(boardGame[row][col] == SquareStatus.SEA.getValue()) {
            boardGame[row][col] = SquareStatus.MISSED.getValue();
        }
        return boardGame;
    }

    private void reduceShipSize(Coordinates coordinates) {
        Optional<Ship> ship = ships.stream().filter(sh -> sh.getCoordinates().contains(coordinates)).findFirst();
        ship.ifPresent(Ship::reduceShipSize);

        ship.ifPresent(sh -> {
            if(sh.getSize() == 0) { // if ship has sinked
                System.out.println("Ship has sinked");
            }
        });
    }
}

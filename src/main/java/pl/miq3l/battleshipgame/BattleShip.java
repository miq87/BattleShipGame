package pl.miq3l.battleshipgame;

import pl.miq3l.battleshipgame.model.BoardGame;
import pl.miq3l.battleshipgame.model.Coordinates;
import pl.miq3l.battleshipgame.model.Ship;
import pl.miq3l.battleshipgame.model.SquareStatus;

import java.util.*;

public class BattleShip {

    private final BoardGame boardGame;
    private static BattleShip INSTANCE;
    private static final int BOARDSIZE = 10;

    public BoardGame get() {
        return boardGame;
    }

    public static BattleShip getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new BattleShip(BOARDSIZE);
        }
        return INSTANCE;
    }

    private BattleShip(int size) {
        this.boardGame = new BoardGame(BOARDSIZE);
    }

    public BoardGame generateShips() {
        boardGame.reset();
        int numberOfShips = 3;

        while(numberOfShips == 3) {
            if(generateSingleShip(5))
                numberOfShips--;
        }
        while(numberOfShips > 0) {
            if(generateSingleShip(4))
                numberOfShips--;
        }
        boardGame.setMessage("New ships created on the board.");
        return boardGame;
    }

    private void saveShipToBoardGame(Ship ship) {
        ship.getCoordinates().forEach(coordinate -> {
            this.boardGame.getBoard()[coordinate.getRow()][coordinate.getCol()]
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
                            if(boardGame.getBoard()[row+x][col+i+y] != SquareStatus.SEA.getValue())
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
                            if(boardGame.getBoard()[row+i+x][col+y] != SquareStatus.SEA.getValue())
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
        boardGame.getShips().add(ship);
        saveShipToBoardGame(ship);
        return true;
    }

    public BoardGame hit(Coordinates coordinates) {
        int row = coordinates.getRow();
        int col = coordinates.getCol();
        if(boardGame.getBoard()[row][col] == SquareStatus.SHIP.getValue()) {
            boardGame.getBoard()[row][col] = SquareStatus.HITTED.getValue();
            reduceShipSize(coordinates);
        }
        else if(boardGame.getBoard()[row][col] == SquareStatus.SEA.getValue()) {
            boardGame.getBoard()[row][col] = SquareStatus.MISSED.getValue();
            boardGame.setMessage("You missed! :( Try again!");
        }
        return boardGame;
    }

    private void reduceShipSize(Coordinates coordinates) {
        Optional<Ship> ship = getShipByCoordinates(coordinates);
        ship.ifPresent(Ship::reduceShipSize);

        ship.ifPresent(sh -> {
            if(sh.getSize() == 0) { // if ship has sinked
                boardGame.setMessage("Congratulations! Hit and sink! :)");
                boardGame.getShips().remove(sh); // remove itself from ships list
            }
            else {
                boardGame.setMessage("Super! Hitted! :)");
            }
        });
        checkIsGameFinished();
    }

    private void checkIsGameFinished() {
        if(boardGame.getShips().size() == 0) {
            boardGame.setMessage("Congratulations! All the ships have sunk!");
        }
    }

    private Optional<Ship> getShipByCoordinates(Coordinates coordinates) {
        return boardGame.getShips().stream()
                .filter(sh -> sh.getCoordinates().contains(coordinates)).findFirst();
    }

}

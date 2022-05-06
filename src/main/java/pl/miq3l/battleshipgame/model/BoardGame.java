package pl.miq3l.battleshipgame.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class BoardGame {
    private int[][] board;
    private String message = "The game has started!";
    private final List<Ship> ships = new ArrayList<>();

    public BoardGame(int boardSize) {
        this.board = new int[boardSize][boardSize];

    }

    public void reset() {
        ships.clear();
        Arrays.stream(board).forEach(a -> {
            Arrays.fill(a, 0);
        });
        setMessage("The board has been cleared.");
    }
}

package pl.miq3l.battleshipgame.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BoardGame {
    private int[][] board;
    private String message;
    private final List<Ship> ships = new ArrayList<>();

    public BoardGame(int boardSize) {
        this.board = new int[boardSize][boardSize];
    }
}

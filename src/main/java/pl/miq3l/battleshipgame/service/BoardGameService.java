package pl.miq3l.battleshipgame.service;

import org.springframework.stereotype.Service;
import pl.miq3l.battleshipgame.BoardGame;

@Service
public class BoardGameService {

    private final BoardGame boardGame;

    public BoardGameService() {
        this.boardGame = BoardGame.getInstance();
    }

    public int[][] getBoardGame() {
        return boardGame.getBoardGame();
    }

    public int[][] resetBoardGame() {
        return boardGame.resetBoardGame();
    }

    public int[][] generateShips() {
        return boardGame.generateShips();
    }

    public int[][] hit(int row, int col) {
        return boardGame.hit(row, col);
    }
}
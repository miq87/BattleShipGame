package pl.miq3l.battleshipgame.service;

import org.springframework.stereotype.Service;
import pl.miq3l.battleshipgame.BoardGame;
import pl.miq3l.battleshipgame.model.Coordinates;

@Service
public class BattleShipService {

    private final BoardGame boardGame;

    public BattleShipService() {
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

    public int[][] hit(Coordinates coordinates) {
        return boardGame.hit(coordinates);
    }
}

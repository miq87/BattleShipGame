package pl.miq3l.battleshipgame.service;

import org.springframework.stereotype.Service;
import pl.miq3l.battleshipgame.BattleShip;
import pl.miq3l.battleshipgame.model.BoardGame;
import pl.miq3l.battleshipgame.model.Coordinates;

@Service
public class BattleShipService {

    private final BattleShip battleShip;

    public BattleShipService() {
        this.battleShip = BattleShip.getInstance();
    }

    public BoardGame get() {
        return battleShip.get();
    }

    public BoardGame create() {
        return battleShip.generateShips();
    }

    public BoardGame hit(Coordinates coordinates) {
        return battleShip.hit(coordinates);
    }
}

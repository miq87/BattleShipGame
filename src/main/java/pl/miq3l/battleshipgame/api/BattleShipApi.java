package pl.miq3l.battleshipgame.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.miq3l.battleshipgame.model.Coordinates;
import pl.miq3l.battleshipgame.service.BattleShipService;

@RestController
@RequestMapping("/api/battleship")
public class BattleShipApi {

    private final BattleShipService boardGameService;

    @Autowired
    public BattleShipApi(BattleShipService boardGameService) {
        this.boardGameService = boardGameService;
    }

    @GetMapping
    public int[][] getBoardGame() {
        return boardGameService.getBoardGame();
    }

    @GetMapping("/reset")
    public int[][] resetBoardGame() {
        return boardGameService.resetBoardGame();
    }

    @GetMapping("/generate")
    public int[][] generateShips() {
        return boardGameService.generateShips();
    }

    @PostMapping("/hit")
    public int[][] hit(@RequestBody Coordinates coordinates) {
        return boardGameService.hit(coordinates);
    }

}

package pl.miq3l.battleshipgame.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.miq3l.battleshipgame.model.BoardGame;
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
    public BoardGame get() {
        return boardGameService.get();
    }

    @GetMapping("/create")
    public BoardGame create() {
        return boardGameService.create();
    }

    @PostMapping("/hit")
    public BoardGame hit(@RequestBody Coordinates coordinates) {
        return boardGameService.hit(coordinates);
    }

}

package pl.miq3l.battleshipgame.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.miq3l.battleshipgame.service.BoardGameService;

@RestController
@RequestMapping("/api/battleship")
public class BoardGameApi {

    private final BoardGameService boardGameService;

    @Autowired
    public BoardGameApi(BoardGameService boardGameService) {
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

    @GetMapping("/hit/{row}/{col}")
    public int[][] hit(@PathVariable int row, @PathVariable int col) {
        return boardGameService.hit(row, col);
    }

}
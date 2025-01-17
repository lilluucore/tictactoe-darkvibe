package com.example.tictactoedarkvibe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TicTacToeController {

    @Autowired
    private TicTacToeService ticTacToeService;

    @PostMapping("/move")
    public String makeMove(@RequestParam int row, @RequestParam int col, Model model) {
        ticTacToeService.makeMove(row, col);
        Game game = ticTacToeService.getCurrentGame();
        model.addAttribute("game", game);
        model.addAttribute("asciiArt", ticTacToeService.getAsciiArt());
        model.addAttribute("insults", game.getInsults());
        model.addAttribute("quotes", game.getQuotes());
        if (game.getLives() == 0) {
            model.addAttribute("showQuote", true);
        }
        return "index";
    }

    @GetMapping("/new-game")
    public String newGame(Model model) {
        Game game = ticTacToeService.newGame();
        model.addAttribute("game", game);
        model.addAttribute("asciiArt", ticTacToeService.getAsciiArt());
        model.addAttribute("insults", game.getInsults());
        model.addAttribute("quotes", game.getQuotes());
        return "index";
    }

    @GetMapping("/reset-game")
    public String resetGame(Model model) {
        ticTacToeService.resetGame();
        Game game = ticTacToeService.getCurrentGame();
        model.addAttribute("game", game);
        model.addAttribute("asciiArt", ticTacToeService.getAsciiArt());
        model.addAttribute("insults", game.getInsults());
        model.addAttribute("quotes", game.getQuotes());
        return "index";
    }
}

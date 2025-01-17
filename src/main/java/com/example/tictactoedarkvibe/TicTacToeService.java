package com.example.tictactoedarkvibe;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class TicTacToeService {

    private Game currentGame;
    private String asciiArt;
    private int totalLives = 3;

    @PostConstruct
    public void init() throws IOException {
        asciiArt = new String(Files.readAllBytes(Paths.get(new ClassPathResource("asciiArt.txt").getURI())));
    }

    public Game newGame() {
        currentGame = new Game();
        currentGame.setLives(totalLives);
        return currentGame;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void makeMove(int row, int col) {
        currentGame.makeMove(row, col);
        totalLives = currentGame.getLives();
    }

    public String getAsciiArt() {
        return asciiArt;
    }

    public void resetGame() {
        currentGame = new Game();
        currentGame.setLives(totalLives);
    }
}

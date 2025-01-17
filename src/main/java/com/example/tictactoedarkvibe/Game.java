package com.example.tictactoedarkvibe;

import java.util.Random;

public class Game {
    private char[][] board;
    private int lives;
    private String message;
    private boolean gameOver;
    private int wins;
    private int losses;
    private static final String[] INSULTS = {
            "例のバカ", "何をやっているんだ", "もっと頑張れ", "残念！", "もう一度挑戦してみろ！",
            "次こそは勝つんだ！", "まだまだだな！", "もっと集中しろ！", "がんばって！", "アニメクレベティックナリ！",
            "勉強するでいるんだ", "もっと努力しろ", "次こそは勝つんだ！", "まだまだだな！", "もっと集中しろ！",
            "お前の負けだ！", "もっと頑張れよ！", "次こそは勝つんだ！", "まだまだだな！", "もっと集中しろ！",
            "もう一度挑戦してみろ！", "何をやっているんだ", "残念！", "がんばって！", "アニメクレベティックナリ！",
            "勉強するでいるんだ", "もっと努力しろ", "次こそは勝つんだ！", "まだまだだな！", "もっと集中しろ！"
    };
    private static final String[] QUOTES = {
            "努力は必ず報われる。", "成功は努力の結果である。", "努力は必ず実を結ぶ。", "努力は必ず報われる。", "努力は必ず実を結ぶ。",
            "成功は努力の結果である。", "努力は必ず実を結ぶ。", "努力は必ず報われる。", "努力は必ず実を結ぶ。", "努力は必ず報われる。",
            "成功は努力の結果である。", "努力は必ず実を結ぶ。", "努力は必ず報われる。", "努力は必ず実を結ぶ。", "努力は必ず報われる。"
    };
    private static final Random RANDOM = new Random();

    public Game() {
        board = new char[3][3];
        lives = 3;
        wins = 0;
        losses = 0;
        message = getRandomInsult();
        gameOver = false;
    }

    public void makeMove(int row, int col) {
        if (board[row][col] == 0 && !gameOver) {
            board[row][col] = 'X';
            if (checkWin('X')) {
                gameOver = true;
                wins++;
                message = "おめでとう！";
            } else if (isBoardFull()) {
                gameOver = true;
                message = "引き分け！";
            } else {
                computerMove();
                if (checkWin('O')) {
                    gameOver = true;
                    lives--;
                    losses++;
                    message = getRandomInsult();
                    if (lives == 0) {
                        message = getRandomQuote();
                    }
                }
            }
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public String getMessage() {
        return message;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getLosses() {
        return losses;
    }

    public String[] getQuotes() {
        return QUOTES;
    }

    public String[] getInsults() {
        return INSULTS;
    }

    private String getRandomInsult() {
        return INSULTS[RANDOM.nextInt(INSULTS.length)];
    }

    private String getRandomQuote() {
        return QUOTES[RANDOM.nextInt(QUOTES.length)];
    }

    private boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }
        return false;
    }

    private void computerMove() {
        int row, col;
        do {
            row = RANDOM.nextInt(3);
            col = RANDOM.nextInt(3);
        } while (board[row][col] != 0);
        board[row][col] = 'O';
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}

package com.baris.mancala.model;

public class Game {
    private int[] board;

    public Game() {
        board = new int[]{6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0};
    }

    public int[] getBoard() {
        return board;
    }

    public void setBoard(int[] board) {
        this.board = board;
    }
}

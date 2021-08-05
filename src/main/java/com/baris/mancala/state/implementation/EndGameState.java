package com.baris.mancala.state.implementation;

import com.baris.mancala.model.Game;
import com.baris.mancala.model.enums.State;
import com.baris.mancala.state.model.AbstractGameState;

import java.util.Arrays;
import java.util.stream.IntStream;

public class EndGameState extends AbstractGameState {
    @Override
    public State getCurrentState() {
        return State.END;
    }

    @Override
    public Game endGame(Game game) {
        int[] board = game.getBoard();

        int player2Leftovers = IntStream.of(Arrays.copyOfRange(board, 0, PLAYER2_BIG_PIT)).sum();
        int player1Leftovers = IntStream.of(Arrays.copyOfRange(board, 7, PLAYER1_BIG_PIT)).sum();

        int player1Score = board[PLAYER1_BIG_PIT] + player1Leftovers;
        int player2Score = board[PLAYER2_BIG_PIT] + player2Leftovers;

        board = new int[]{0, 0, 0, 0, 0, 0, player2Score, 0, 0, 0, 0, 0, 0, player1Score};
        game.setBoard(board);
        return game;
    }
}

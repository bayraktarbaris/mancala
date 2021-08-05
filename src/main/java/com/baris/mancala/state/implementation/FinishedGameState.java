package com.baris.mancala.state.implementation;

import com.baris.mancala.model.Game;
import com.baris.mancala.model.enums.Side;
import com.baris.mancala.model.enums.State;
import com.baris.mancala.state.model.AbstractGameState;

public class FinishedGameState extends AbstractGameState {
    @Override
    public State getCurrentState() {
        return State.FINISHED;
    }

    @Override
    public Game initializeGame() {
        return new Game();
    }

    @Override
    public Side[] declareWinner(Game game) {
        int[] board = game.getBoard();

        if (board[PLAYER2_BIG_PIT] > board[PLAYER1_BIG_PIT]) {
            return new Side[]{Side.PLAYER2};
        } else if (board[PLAYER1_BIG_PIT] > board[PLAYER2_BIG_PIT]) {
            return new Side[]{Side.PLAYER1};
        } else {
            return new Side[]{Side.PLAYER2, Side.PLAYER1};
        }
    }
}

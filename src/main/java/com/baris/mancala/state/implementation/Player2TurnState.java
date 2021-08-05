package com.baris.mancala.state.implementation;

import com.baris.mancala.exception.IllegalMoveException;
import com.baris.mancala.exception.UnsupportedOperationException;
import com.baris.mancala.model.Game;
import com.baris.mancala.model.enums.Side;
import com.baris.mancala.model.enums.State;
import com.baris.mancala.state.GameStateFactory;
import com.baris.mancala.state.model.AbstractGameState;
import com.baris.mancala.state.model.IGameState;

public class Player2TurnState extends AbstractGameState {
    @Override
    public State getCurrentState() {
        return State.PLAYER2;
    }

    @Override
    public IGameState play(Game game, int pit) throws IllegalMoveException, UnsupportedOperationException {
        validateMove(Side.PLAYER2, game, pit);
        int index = sow(game, PLAYER1_BIG_PIT, pit);
        if (index == PLAYER2_BIG_PIT) {
            return isGameOver(game) ? GameStateFactory.getGameState(State.END) : GameStateFactory.getGameState(State.PLAYER2);
        } else if (index < PLAYER2_BIG_PIT) {
            int[] board = game.getBoard();
            if (board[index] == 1) {
                int stolenStones = board[12 - index];
                board[12 - index] = 0;
                board[index] = 0;
                board[PLAYER2_BIG_PIT] = board[PLAYER2_BIG_PIT] + stolenStones + 1;
                game.setBoard(board);
            }
            return isGameOver(game) ? GameStateFactory.getGameState(State.END) : GameStateFactory.getGameState(State.PLAYER1);
        } else {
            return isGameOver(game) ? GameStateFactory.getGameState(State.END) : GameStateFactory.getGameState(State.PLAYER1);
        }
    }
}

package com.baris.mancala.service;

import com.baris.mancala.exception.IllegalMoveException;
import com.baris.mancala.exception.UnsupportedOperationException;
import com.baris.mancala.model.enums.Side;
import com.baris.mancala.model.enums.State;

public interface IGameService {
    /**
     * Gets current state of the game.
     *
     * @return returns @State enum, according to game's current state.
     */
    State getCurrentState();

    /**
     * Gets current state of the board.
     *
     * @return Returns an integer array representation of the board.
     * @throws UnsupportedOperationException Thrown when board isn't created yet, in State.NEW.
     */
    int[] getBoard() throws UnsupportedOperationException;

    /**
     * Starts a new game by initializing the board.
     * Switches state to State.PLAYER1 immediately, as Player 1 always starts first.
     *
     * @throws UnsupportedOperationException Thrown when an unfinished game already exists.
     */
    void startNewGame() throws UnsupportedOperationException;

    /**
     * Plays from the pit specified
     *
     * @param pit id of the pit
     * @throws IllegalMoveException          Thrown when specified move is illegal.
     * @throws UnsupportedOperationException Thrown when game isn't in a state where you can make a move.
     */
    void play(int pit) throws IllegalMoveException, UnsupportedOperationException;

    /**
     * Returns the side of who won the game. Returns both sides if it's a draw.
     *
     * @return Side of the winner(s).
     * @throws UnsupportedOperationException Thrown when the winner isn't decided yet.
     */
    Side[] declareWinner() throws UnsupportedOperationException;
}

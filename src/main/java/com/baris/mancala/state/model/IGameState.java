package com.baris.mancala.state.model;

import com.baris.mancala.model.Game;
import com.baris.mancala.model.enums.Side;
import com.baris.mancala.model.enums.State;
import com.baris.mancala.exception.IllegalMoveException;
import com.baris.mancala.exception.UnsupportedOperationException;

public interface IGameState {
    /**
     * Gets current state of the game
     *
     * @return returns @State enum, according to game's current state
     */
    State getCurrentState();

    /**
     * Initializes the game by creating a fresh @Game instance
     *
     * @return returns the instance created
     * @throws UnsupportedOperationException Thrown when an unfinished game already exists.
     */
    Game initializeGame() throws UnsupportedOperationException;

    /**
     * Plays from the pit specified, on the game specified.
     *
     * @param game instance of the current game
     * @param pit  id of the pit
     * @return returns what comes next: Player 2 plays - Player 1 plays - or the game is over.
     * @throws UnsupportedOperationException Thrown when game isn't in a state where you can make a move.
     * @throws IllegalMoveException          Thrown when specified move is illegal.
     */
    IGameState play(Game game, int pit) throws UnsupportedOperationException, IllegalMoveException;

    /**
     * Finishes the game when no stone left in one of sides by counting leftover stones.
     *
     * @param game instance of the current game.
     * @return returns the finished game.
     * @throws UnsupportedOperationException Thrown when game isn't ready to be finished.
     */
    Game endGame(Game game) throws UnsupportedOperationException;

    /**
     * Returns the side of who won the game. Returns both sides if it's a draw.
     *
     * @return Side of the winner(s).
     * @throws UnsupportedOperationException Thrown when the winner isn't decided yet.
     */
    Side[] declareWinner(Game game) throws UnsupportedOperationException;
}

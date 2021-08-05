package com.baris.mancala.state.model;

import com.baris.mancala.model.Game;
import com.baris.mancala.model.enums.Side;
import com.baris.mancala.exception.IllegalMoveException;
import com.baris.mancala.exception.UnsupportedOperationException;

import java.util.Arrays;
import java.util.stream.IntStream;

public abstract class AbstractGameState implements IGameState {
    protected final int PLAYER2_BIG_PIT = 6;
    protected final int PLAYER1_BIG_PIT = 13;

    @Override
    public Game initializeGame() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("You can't initialize a new game now.");
    }

    @Override
    public IGameState play(Game game, int pit) throws UnsupportedOperationException, IllegalMoveException {
        throw new UnsupportedOperationException("You can't play now.");
    }

    @Override
    public Game endGame(Game game) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("You can't end game now. There are still stones in both sides.");
    }

    @Override
    public Side[] declareWinner(Game game) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Game isn't finished yet.");
    }

    protected void validateMove(Side side, Game game, int pit) throws IllegalMoveException {
        if (pit < 0 || pit == PLAYER2_BIG_PIT || pit >= PLAYER1_BIG_PIT) {
            throw new IllegalMoveException("You can't do that.");
        } else if (pit < PLAYER2_BIG_PIT && side == Side.PLAYER1 || (pit > PLAYER2_BIG_PIT && side == Side.PLAYER2)) {
            throw new IllegalMoveException("You can't play from your opponent's pits.");
        } else {
            int[] board = game.getBoard();
            if (board[pit] == 0) {
                throw new IllegalMoveException("There's no stone in pit " + pit + ".");
            }
        }
    }

    /**
     * Sows stones counter clockwise.
     *
     * @param game            Current game instance.
     * @param opponentMancala Opponent's Mancala ID.
     * @param pit             Which pit player is starting to sow stones from.
     * @return Returns the ID of the pit where it puts its last stone.
     */
    protected int sow(Game game, int opponentMancala, int pit) {
        int[] board = game.getBoard();
        int stoneCount = board[pit];
        int currentIndex = pit;

        board[pit] = 0;
        while (stoneCount > 0) {
            currentIndex = (currentIndex + 1) % 14;
            if (currentIndex != opponentMancala) {
                board[currentIndex]++;
                stoneCount--;
            }
        }
        game.setBoard(board);
        return currentIndex;
    }

    /**
     * Returns if game is over. Checks if there are stones either sides.
     *
     * @param game Current game instance.
     * @return Returns if the game is over or not.
     */
    protected boolean isGameOver(Game game) {
        int[] board = game.getBoard();
        int[] player2 = Arrays.copyOfRange(board, 0, PLAYER2_BIG_PIT);
        int[] player1 = Arrays.copyOfRange(board, 7, PLAYER1_BIG_PIT);

        return IntStream.of(player2).allMatch(x -> x == 0) || IntStream.of(player1).allMatch(x -> x == 0);
    }
}

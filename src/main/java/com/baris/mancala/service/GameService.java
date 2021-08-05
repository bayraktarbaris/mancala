package com.baris.mancala.service;

import com.baris.mancala.exception.IllegalMoveException;
import com.baris.mancala.exception.UnsupportedOperationException;
import com.baris.mancala.model.Game;
import com.baris.mancala.model.enums.Side;
import com.baris.mancala.model.enums.State;
import com.baris.mancala.state.GameStateFactory;
import org.springframework.stereotype.Service;

@Service
public class GameService implements IGameService {
    private com.baris.mancala.state.model.IGameState IGameState;
    private Game game;

    public GameService() throws UnsupportedOperationException {
        IGameState = GameStateFactory.getGameState(State.NEW);
    }

    @Override
    public State getCurrentState() {
        return IGameState.getCurrentState();
    }

    @Override
    public int[] getBoard() throws UnsupportedOperationException {
        if (game == null) {
            throw new UnsupportedOperationException("Game isn't started yet");
        } else {
            return game.getBoard();
        }
    }

    @Override
    public synchronized void startNewGame() throws UnsupportedOperationException {
        game = IGameState.initializeGame();
        IGameState = GameStateFactory.getGameState(State.PLAYER2);
    }

    @Override
    public synchronized void play(int pit) throws IllegalMoveException, UnsupportedOperationException {
        IGameState = IGameState.play(game, pit);
        if (IGameState.getCurrentState() == State.END) {
            endGame();
        }
    }

    @Override
    public Side[] declareWinner() throws UnsupportedOperationException {
        return IGameState.declareWinner(game);
    }

    /**
     * Since State.END is a transition state before State.FINISHED, there is no direct access to this method
     *
     * @throws UnsupportedOperationException thrown when there are stones in both sides
     */
    private void endGame() throws UnsupportedOperationException {
        game = IGameState.endGame(game);
        IGameState = GameStateFactory.getGameState(State.FINISHED);
    }
}

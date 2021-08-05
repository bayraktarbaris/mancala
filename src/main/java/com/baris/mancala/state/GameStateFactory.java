package com.baris.mancala.state;

import com.baris.mancala.exception.UnsupportedOperationException;
import com.baris.mancala.model.enums.State;
import com.baris.mancala.state.implementation.*;
import com.baris.mancala.state.model.IGameState;

public class GameStateFactory {
    public static IGameState getGameState(State state) throws UnsupportedOperationException {
        if (state != null) {
            switch (state) {
                case NEW:
                    return new NewGameState();
                case PLAYER2:
                    return new Player2TurnState();
                case PLAYER1:
                    return new Player1TurnState();
                case END:
                    return new EndGameState();
                case FINISHED:
                    return new FinishedGameState();
            }
        }
        throw new UnsupportedOperationException("Requested an unknown state from GameStateFactory.");
    }
}

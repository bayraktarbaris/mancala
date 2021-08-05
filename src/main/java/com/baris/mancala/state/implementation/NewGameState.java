package com.baris.mancala.state.implementation;

import com.baris.mancala.model.Game;
import com.baris.mancala.model.enums.State;
import com.baris.mancala.state.model.AbstractGameState;

public class NewGameState extends AbstractGameState {
    @Override
    public State getCurrentState() {
        return State.NEW;
    }

    @Override
    public Game initializeGame() {
        return new Game();
    }
}

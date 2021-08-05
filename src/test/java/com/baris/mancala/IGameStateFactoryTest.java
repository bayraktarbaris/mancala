package com.baris.mancala;

import com.baris.mancala.state.GameStateFactory;
import com.baris.mancala.exception.UnsupportedOperationException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class IGameStateFactoryTest {

    @Test
    public void testGameStateFactoryException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> GameStateFactory.getGameState(null));
    }
}

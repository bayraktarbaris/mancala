package com.baris.mancala;

import com.baris.mancala.model.Game;
import com.baris.mancala.model.enums.Side;
import com.baris.mancala.model.enums.State;
import com.baris.mancala.exception.IllegalMoveException;
import com.baris.mancala.exception.UnsupportedOperationException;
import com.baris.mancala.service.IGameService;
import com.baris.mancala.service.GameService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.TestComponent;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@TestComponent
public class IGameServiceTest {

    private IGameService IGameService;

    @Before
    public void setUp() throws UnsupportedOperationException {
        IGameService = new GameService();
    }

    @After
    public void tearDown() {
        IGameService = null;
    }

    @Test
    public void testGetCurrentState() throws Exception {
        Assert.assertEquals(State.NEW, IGameService.getCurrentState());
        IGameService.startNewGame();
        Assert.assertEquals(State.PLAYER2, IGameService.getCurrentState());
        IGameService.play(5);
        Assert.assertEquals(State.PLAYER1, IGameService.getCurrentState());
        IGameService.play(12);
        Assert.assertEquals(State.PLAYER2, IGameService.getCurrentState());

        //Fast forwarding to end game, using reflection
        Game game = new Game();
        game.setBoard(new int[]{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0});
        modifyGame(game);

        //Cannot test State.END because it's just a transition state before deciding who the winner is.
        IGameService.play(5);
        Assert.assertEquals(State.FINISHED, IGameService.getCurrentState());
    }

    @Test
    public void testPlay() throws UnsupportedOperationException, NoSuchFieldException, IllegalAccessException, IllegalMoveException {
        IGameService.startNewGame();

        //Testing Player 2's steal
        Game game = new Game();
        game.setBoard(new int[]{1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 5, 0, 0});
        modifyGame(game);
        IGameService.play(0);
        Assert.assertArrayEquals(new int[]{0, 0, 0, 0, 0, 1, 6, 1, 0, 0, 0, 0, 0, 0}, IGameService.getBoard());

        //Testing Player 1's steal
        game.setBoard(new int[]{1, 0, 0, 0, 5, 0, 0, 1, 0, 0, 0, 0, 1, 0});
        modifyGame(game);
        IGameService.play(12);
        IGameService.play(7);
        Assert.assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 7}, IGameService.getBoard());
    }

    @Test
    public void testGetBoard() throws Exception {
        Assert.assertThrows(UnsupportedOperationException.class, IGameService::getBoard);

        Game game = new Game();
        modifyGame(game);
        Assert.assertEquals(game.getBoard(), IGameService.getBoard());
    }

    @Test
    public void testDeclareWinner() throws UnsupportedOperationException, NoSuchFieldException, IllegalAccessException, IllegalMoveException {
        Assert.assertThrows(UnsupportedOperationException.class, IGameService::declareWinner);

        //Player 2 wins !
        IGameService.startNewGame();
        Game game = new Game();
        game.setBoard(new int[]{0, 0, 0, 0, 0, 1, 20, 0, 0, 0, 0, 0, 1, 0});
        modifyGame(game);
        IGameService.play(5);
        Assert.assertArrayEquals(new Side[]{Side.PLAYER2}, IGameService.declareWinner());

        //Player 1 wins !
        IGameService.startNewGame();
        game.setBoard(new int[]{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 20});
        modifyGame(game);
        IGameService.play(5);
        Assert.assertArrayEquals(new Side[]{Side.PLAYER1}, IGameService.declareWinner());

        //It's a tie :(
        IGameService.startNewGame();
        game.setBoard(new int[]{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0});
        modifyGame(game);
        IGameService.play(5);
        Assert.assertArrayEquals(new Side[]{Side.PLAYER2, Side.PLAYER1}, IGameService.declareWinner());
    }

    @Test
    public void testIllegalMove() throws UnsupportedOperationException, IllegalMoveException {
        IGameService.startNewGame();
        Assert.assertThrows(IllegalMoveException.class, () -> IGameService.play(11));
        Assert.assertThrows(IllegalMoveException.class, () -> IGameService.play(6));
        IGameService.play(0);
        Assert.assertThrows(IllegalMoveException.class, () -> IGameService.play(0));
    }

    @Test
    public void testIllegalStateActions() throws UnsupportedOperationException, NoSuchMethodException {
        Assert.assertThrows(UnsupportedOperationException.class, () -> IGameService.play(0));
        IGameService.startNewGame();
        Assert.assertThrows(UnsupportedOperationException.class, IGameService::startNewGame);

        Method endGameMethod = IGameService.getClass().getDeclaredMethod("endGame");
        endGameMethod.setAccessible(true);
        //We should expect InvocationTargetException instead of MancalaException here, because we've
        //added another layer of abstraction with reflection. If you trace its stack trace, you'll
        //find our UnsupportedOperationException anyways.
        Assert.assertThrows(InvocationTargetException.class, () -> endGameMethod.invoke(IGameService));
    }

    private void modifyGame(Game game) throws NoSuchFieldException, IllegalAccessException {
        Field gameField = IGameService.getClass().getDeclaredField("game");
        gameField.setAccessible(true);
        gameField.set(IGameService, game);
    }

}

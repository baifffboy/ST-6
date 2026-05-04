package com.mycompany.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;
    private Player playerX;
    private Player playerO;

    @BeforeEach
    void setUp() {
        game = new Game();
        playerX = game.player1;
        playerO = game.player2;
        game.symbol = 'X';
    }

    @Test
    void testConstructor() {
        assertNotNull(game.board);
        assertEquals(9, game.board.length);
        for (char c : game.board) {
            assertEquals(' ', c);
        }
        assertEquals(State.PLAYING, game.state);
        assertEquals('X', game.player1.symbol);
        assertEquals('O', game.player2.symbol);
        assertEquals(100, Game.INF);
    }

    @Test
    void testCheckStateXWinHorizontal() {
        char[] board = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';
        State result = game.checkState(board);
        assertEquals(State.XWIN, result);
    }

    @Test
    void testCheckStateXWinVertical() {
        char[] board = {'X', ' ', ' ', 'X', ' ', ' ', 'X', ' ', ' '};
        game.symbol = 'X';
        State result = game.checkState(board);
        assertEquals(State.XWIN, result);
    }

    @Test
    void testCheckStateXWinDiagonal() {
        char[] board = {'X', ' ', ' ', ' ', 'X', ' ', ' ', ' ', 'X'};
        game.symbol = 'X';
        State result = game.checkState(board);
        assertEquals(State.XWIN, result);
    }

    @Test
    void testCheckStateXWinDiagonal2() {
        char[] board = {' ', ' ', 'X', ' ', 'X', ' ', 'X', ' ', ' '};
        game.symbol = 'X';
        State result = game.checkState(board);
        assertEquals(State.XWIN, result);
    }

    @Test
    void testCheckStateOWin() {
        char[] board = {'O', 'O', 'O', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'O';
        State result = game.checkState(board);
        assertEquals(State.OWIN, result);
    }

    @Test
    void testCheckStateDraw() {
        char[] board = {'X', 'O', 'X', 'O', 'X', 'O', 'O', 'X', 'O'};
        game.symbol = 'X';
        State result = game.checkState(board);
        assertEquals(State.DRAW, result);
    }

    @Test
    void testCheckStatePlaying() {
        char[] board = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';
        State result = game.checkState(board);
        assertEquals(State.PLAYING, result);
    }

    @Test
    void testGenerateMoves() {
        char[] board = {'X', ' ', 'O', ' ', 'X', ' ', 'O', ' ', ' '};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);
        assertEquals(5, moves.size());
        assertTrue(moves.contains(1));
        assertTrue(moves.contains(3));
        assertTrue(moves.contains(5));
        assertTrue(moves.contains(7));
        assertTrue(moves.contains(8));
    }

    @Test
    void testGenerateMovesEmptyBoard() {
        char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);
        assertEquals(9, moves.size());
    }

    @Test
    void testGenerateMovesFullBoard() {
        char[] board = {'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X'};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);
        assertEquals(0, moves.size());
    }

    @Test
    void testEvaluatePositionXWinForX() {
        char[] board = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';
        int result = game.evaluatePosition(board, playerX);
        assertEquals(Game.INF, result);
    }

    @Test
    void testEvaluatePositionXWinForO() {
        char[] board = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';
        int result = game.evaluatePosition(board, playerO);
        assertEquals(-Game.INF, result);
    }

    @Test
    void testEvaluatePositionOWinForO() {
        char[] board = {'O', 'O', 'O', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'O';
        int result = game.evaluatePosition(board, playerO);
        assertEquals(Game.INF, result);
    }

    @Test
    void testEvaluatePositionDraw() {
        char[] board = {'X', 'O', 'X', 'O', 'X', 'O', 'O', 'X', 'O'};
        int result = game.evaluatePosition(board, playerX);
        assertEquals(0, result);
    }

    @Test
    void testEvaluatePositionNotTerminal() {
        char[] board = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        int result = game.evaluatePosition(board, playerX);
        assertEquals(-1, result);
    }

    @Test
    void testMiniMaxEmptyBoard() {
        char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        int move = game.MiniMax(board, playerX);
        assertTrue(move >= 1 && move <= 9);
    }

    @Test
    void testMiniMaxAlmostFullBoard() {
        char[] board = {'X', 'O', 'X', 'O', 'X', 'O', 'O', 'X', ' '};
        int move = game.MiniMax(board, playerX);
        assertEquals(9, move);
    }

    @Test
    void testMinMove() {
        char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        int result = game.MinMove(board, playerX);
        assertTrue(result >= -Game.INF && result <= Game.INF);
    }

    @Test
    void testMaxMove() {
        char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        int result = game.MaxMove(board, playerX);
        assertTrue(result >= -Game.INF && result <= Game.INF);
    }

    @Test
    void testMinMoveTerminalPosition() {
        char[] board = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';
        int result = game.MinMove(board, playerX);
        assertEquals(Game.INF, result);
    }
}

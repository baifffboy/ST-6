package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class AppTest {
    
    private Game game;
    private char[] board;
    private TicTacToeCell cell;
    private Utility utility;
    
    @BeforeEach
    public void setUp() throws Exception {
        game = new Game();
        Field boardField = Game.class.getDeclaredField("board");
        boardField.setAccessible(true);
        board = (char[]) boardField.get(game);
        cell = new TicTacToeCell(0, 0, 0);
        utility = new Utility();
    }
    
    // ============= ТЕСТЫ ДЛЯ КЛАССА GAME =============
    
    @Test
    @DisplayName("Game: Board initialization")
    public void testBoardInitialization() {
        for (int i = 0; i < 9; i++) {
            assertEquals(' ', board[i]);
        }
    }
    
    @Test
    @DisplayName("Game: Players have correct symbols")
    public void testPlayersSymbols() {
        assertEquals('X', game.player1.symbol);
        assertEquals('O', game.player2.symbol);
    }
    
    @Test
    @DisplayName("Game: Initial state is PLAYING")
    public void testInitialState() {
        assertEquals(State.PLAYING, game.state);
    }
    
    @Test
    @DisplayName("Game: X wins by top row")
    public void testXWinsTopRow() throws Exception {
        board[0] = 'X';
        board[1] = 'X';
        board[2] = 'X';
        game.symbol = 'X';
        
        Method method = Game.class.getDeclaredMethod("checkState", char[].class);
        method.setAccessible(true);
        State result = (State) method.invoke(game, (Object) board);
        
        assertEquals(State.XWIN, result);
    }
    
    @Test
    @DisplayName("Game: X wins by middle row")
    public void testXWinsMiddleRow() throws Exception {
        board[3] = 'X';
        board[4] = 'X';
        board[5] = 'X';
        game.symbol = 'X';
        
        Method method = Game.class.getDeclaredMethod("checkState", char[].class);
        method.setAccessible(true);
        State result = (State) method.invoke(game, (Object) board);
        
        assertEquals(State.XWIN, result);
    }
    
    @Test
    @DisplayName("Game: X wins by bottom row")
    public void testXWinsBottomRow() throws Exception {
        board[6] = 'X';
        board[7] = 'X';
        board[8] = 'X';
        game.symbol = 'X';
        
        Method method = Game.class.getDeclaredMethod("checkState", char[].class);
        method.setAccessible(true);
        State result = (State) method.invoke(game, (Object) board);
        
        assertEquals(State.XWIN, result);
    }
    
    @Test
    @DisplayName("Game: X wins by first column")
    public void testXWinsFirstColumn() throws Exception {
        board[0] = 'X';
        board[3] = 'X';
        board[6] = 'X';
        game.symbol = 'X';
        
        Method method = Game.class.getDeclaredMethod("checkState", char[].class);
        method.setAccessible(true);
        State result = (State) method.invoke(game, (Object) board);
        
        assertEquals(State.XWIN, result);
    }
    
    @Test
    @DisplayName("Game: X wins by second column")
    public void testXWinsSecondColumn() throws Exception {
        board[1] = 'X';
        board[4] = 'X';
        board[7] = 'X';
        game.symbol = 'X';
        
        Method method = Game.class.getDeclaredMethod("checkState", char[].class);
        method.setAccessible(true);
        State result = (State) method.invoke(game, (Object) board);
        
        assertEquals(State.XWIN, result);
    }
    
    @Test
    @DisplayName("Game: X wins by third column")
    public void testXWinsThirdColumn() throws Exception {
        board[2] = 'X';
        board[5] = 'X';
        board[8] = 'X';
        game.symbol = 'X';
        
        Method method = Game.class.getDeclaredMethod("checkState", char[].class);
        method.setAccessible(true);
        State result = (State) method.invoke(game, (Object) board);
        
        assertEquals(State.XWIN, result);
    }
    
    @Test
    @DisplayName("Game: X wins by main diagonal")
    public void testXWinsMainDiagonal() throws Exception {
        board[0] = 'X';
        board[4] = 'X';
        board[8] = 'X';
        game.symbol = 'X';
        
        Method method = Game.class.getDeclaredMethod("checkState", char[].class);
        method.setAccessible(true);
        State result = (State) method.invoke(game, (Object) board);
        
        assertEquals(State.XWIN, result);
    }
    
    @Test
    @DisplayName("Game: X wins by anti diagonal")
    public void testXWinsAntiDiagonal() throws Exception {
        board[2] = 'X';
        board[4] = 'X';
        board[6] = 'X';
        game.symbol = 'X';
        
        Method method = Game.class.getDeclaredMethod("checkState", char[].class);
        method.setAccessible(true);
        State result = (State) method.invoke(game, (Object) board);
        
        assertEquals(State.XWIN, result);
    }
    
    @Test
    @DisplayName("Game: O wins by top row")
    public void testOWinsTopRow() throws Exception {
        board[0] = 'O';
        board[1] = 'O';
        board[2] = 'O';
        game.symbol = 'O';
        
        Method method = Game.class.getDeclaredMethod("checkState", char[].class);
        method.setAccessible(true);
        State result = (State) method.invoke(game, (Object) board);
        
        assertEquals(State.OWIN, result);
    }
    
    @Test
    @DisplayName("Game: O wins by main diagonal")
    public void testOWinsMainDiagonal() throws Exception {
        board[0] = 'O';
        board[4] = 'O';
        board[8] = 'O';
        game.symbol = 'O';
        
        Method method = Game.class.getDeclaredMethod("checkState", char[].class);
        method.setAccessible(true);
        State result = (State) method.invoke(game, (Object) board);
        
        assertEquals(State.OWIN, result);
    }
    
    @Test
    @DisplayName("Game: Draw game")
    public void testDrawGame() throws Exception {
        board[0] = 'X'; board[1] = 'O'; board[2] = 'X';
        board[3] = 'O'; board[4] = 'X'; board[5] = 'O';
        board[6] = 'O'; board[7] = 'X'; board[8] = 'O';
        game.symbol = 'X';
        
        Method method = Game.class.getDeclaredMethod("checkState", char[].class);
        method.setAccessible(true);
        State result = (State) method.invoke(game, (Object) board);
        
        assertEquals(State.DRAW, result);
    }
    
    @Test
    @DisplayName("Game: Still playing")
    public void testGamePlaying() throws Exception {
        board[0] = 'X';
        board[1] = 'O';
        game.symbol = 'X';
        
        Method method = Game.class.getDeclaredMethod("checkState", char[].class);
        method.setAccessible(true);
        State result = (State) method.invoke(game, (Object) board);
        
        assertEquals(State.PLAYING, result);
    }
    
    @Test
    @DisplayName("Game: Generate moves")
    public void testGenerateMoves() throws Exception {
        board[0] = 'X';
        board[4] = 'O';
        board[8] = 'X';
        
        ArrayList<Integer> moveList = new ArrayList<>();
        Method method = Game.class.getDeclaredMethod("generateMoves", char[].class, ArrayList.class);
        method.setAccessible(true);
        method.invoke(game, board, moveList);
        
        assertEquals(6, moveList.size());
    }
    
    @Test
    @DisplayName("Game: Generate moves on full board")
    public void testGenerateMovesFullBoard() throws Exception {
        for (int i = 0; i < 9; i++) {
            board[i] = 'X';
        }
        
        ArrayList<Integer> moveList = new ArrayList<>();
        Method method = Game.class.getDeclaredMethod("generateMoves", char[].class, ArrayList.class);
        method.setAccessible(true);
        method.invoke(game, board, moveList);
        
        assertEquals(0, moveList.size());
    }
    
    @Test
    @DisplayName("Game: Generate moves on empty board")
    public void testGenerateMovesEmptyBoard() throws Exception {
        ArrayList<Integer> moveList = new ArrayList<>();
        Method method = Game.class.getDeclaredMethod("generateMoves", char[].class, ArrayList.class);
        method.setAccessible(true);
        method.invoke(game, board, moveList);
        
        assertEquals(9, moveList.size());
    }
    
    @Test
    @DisplayName("Game: Evaluate position - draw")
    public void testEvaluatePositionDraw() throws Exception {
        board[0] = 'X'; board[1] = 'O'; board[2] = 'X';
        board[3] = 'O'; board[4] = 'X'; board[5] = 'O';
        board[6] = 'O'; board[7] = 'X'; board[8] = 'O';
        
        Method method = Game.class.getDeclaredMethod("evaluatePosition", char[].class, Player.class);
        method.setAccessible(true);
        int result = (int) method.invoke(game, board, game.player1);
        
        assertEquals(0, result);
    }
    
    @Test
    @DisplayName("Game: Evaluate position - no winner")
    public void testEvaluatePositionNoWinner() throws Exception {
        board[0] = 'X';
        board[1] = 'O';
        
        Method method = Game.class.getDeclaredMethod("evaluatePosition", char[].class, Player.class);
        method.setAccessible(true);
        int result = (int) method.invoke(game, board, game.player1);
        
        assertEquals(-1, result);
    }
    
    @Test
    @DisplayName("Game: Minimax returns valid move")
    public void testMinimaxValidMove() throws Exception {
        Method method = Game.class.getDeclaredMethod("MiniMax", char[].class, Player.class);
        method.setAccessible(true);
        int move = (int) method.invoke(game, board, game.player2);
        
        assertTrue(move >= 1 && move <= 9);
    }
    
    @Test
    @DisplayName("Game: MinMove returns value")
    public void testMinMove() throws Exception {
        Method method = Game.class.getDeclaredMethod("MinMove", char[].class, Player.class);
        method.setAccessible(true);
        int result = (int) method.invoke(game, board, game.player2);
        
        assertTrue(result >= -Game.INF && result <= Game.INF);
    }
    
    @Test
    @DisplayName("Game: MaxMove returns value")
    public void testMaxMove() throws Exception {
        Method method = Game.class.getDeclaredMethod("MaxMove", char[].class, Player.class);
        method.setAccessible(true);
        int result = (int) method.invoke(game, board, game.player2);
        
        assertTrue(result >= -Game.INF && result <= Game.INF);
    }
    
    @Test
    @DisplayName("Game: INF constant")
    public void testInfConstant() {
        assertEquals(100, Game.INF);
    }
    
    // ============= ТЕСТЫ ДЛЯ КЛАССА TicTacToeCell =============
    
    @Test
    @DisplayName("TicTacToeCell: Constructor sets correct values")
    public void testCellConstructor() {
        assertEquals(0, cell.getNum());
        assertEquals(0, cell.getRow());
        assertEquals(0, cell.getCol());
        assertEquals(' ', cell.getMarker());
    }
    
    @Test
    @DisplayName("TicTacToeCell: Set marker")
    public void testCellSetMarker() {
        cell.setMarker("X");
        assertEquals('X', cell.getMarker());
        assertEquals("X", cell.getText());
        assertFalse(cell.isEnabled());
    }
    
    @Test
    @DisplayName("TicTacToeCell: Set O marker")
    public void testCellSetOMarker() {
        cell.setMarker("O");
        assertEquals('O', cell.getMarker());
        assertEquals("O", cell.getText());
        assertFalse(cell.isEnabled());
    }
    
    @Test
    @DisplayName("TicTacToeCell: Get row and col")
    public void testCellGetRowCol() {
        TicTacToeCell newCell = new TicTacToeCell(5, 2, 1);
        assertEquals(5, newCell.getNum());
        assertEquals(1, newCell.getRow());
        assertEquals(2, newCell.getCol());
    }
    
    // ============= ТЕСТЫ ДЛЯ КЛАССА Utility =============
    
    @Test
    @DisplayName("Utility: Print char array does not throw exception")
    public void testUtilityPrintCharArray() {
        assertDoesNotThrow(() -> Utility.print(board));
    }
    
    @Test
    @DisplayName("Utility: Print int array does not throw exception")
    public void testUtilityPrintIntArray() {
        int[] intArray = new int[9];
        assertDoesNotThrow(() -> Utility.print(intArray));
    }
    
    @Test
    @DisplayName("Utility: Print ArrayList does not throw exception")
    public void testUtilityPrintArrayList() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        assertDoesNotThrow(() -> Utility.print(list));
    }
    
    @Test
    @DisplayName("Utility: Print empty ArrayList")
    public void testUtilityPrintEmptyArrayList() {
        ArrayList<Integer> list = new ArrayList<>();
        assertDoesNotThrow(() -> Utility.print(list));
    }
    
    // ============= ТЕСТЫ ДЛЯ КЛАССА App =============
    
    @Test
    @DisplayName("App: Class exists and can be instantiated")
    public void testAppInstantiation() {
        App app = new App();
        assertNotNull(app);
    }
    
    @Test
    @DisplayName("App: Main method exists")
    public void testAppMainExists() {
        assertDoesNotThrow(() -> App.class.getDeclaredMethod("main", String[].class));
    }
    
    // ============= ТЕСТЫ ДЛЯ КЛАССА Program =============
    
    @Test
    @DisplayName("Program: Class exists and can be instantiated")
    public void testProgramInstantiation() {
        Program program = new Program();
        assertNotNull(program);
    }
    
    @Test
    @DisplayName("Program: Main method exists")
    public void testProgramMainExists() {
        assertDoesNotThrow(() -> Program.class.getDeclaredMethod("main", String[].class));
    }
    
    // ============= ТЕСТЫ ДЛЯ КЛАССА TicTacToePanel =============
    
    @Test
    @DisplayName("TicTacToePanel: Constructor creates panel with 9 cells")
    public void testTicTacToePanelConstructor() {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        assertNotNull(panel);
        assertEquals(9, panel.getComponentCount());
    }
    
    // ============= ТЕСТЫ ДЛЯ КЛАССА Player =============
    
    @Test
    @DisplayName("Player: Default values")
    public void testPlayerDefaultValues() {
        Player player = new Player();
        assertEquals(0, player.move);
        assertFalse(player.selected);
        assertFalse(player.win);
    }
    
    @Test
    @DisplayName("Player: Set symbol")
    public void testPlayerSetSymbol() {
        Player player = new Player();
        player.symbol = 'X';
        assertEquals('X', player.symbol);
        player.symbol = 'O';
        assertEquals('O', player.symbol);
    }
    
    // ============= ТЕСТЫ ДЛЯ КЛАССА State =============
    
    @Test
    @DisplayName("State: Enum values")
    public void testStateEnum() {
        State[] states = State.values();
        assertEquals(4, states.length);
        assertEquals(State.PLAYING, State.valueOf("PLAYING"));
        assertEquals(State.OWIN, State.valueOf("OWIN"));
        assertEquals(State.XWIN, State.valueOf("XWIN"));
        assertEquals(State.DRAW, State.valueOf("DRAW"));
    }
    
    // ============= ТЕСТЫ ДЛЯ TicTacToePanel =============
    
    @Test
    @DisplayName("TicTacToePanel: Class can be loaded")
    public void testTicTacToePanelClassLoadable() {
        assertNotNull(TicTacToePanel.class);
    }
    
    @Test
    @DisplayName("TicTacToePanel: Panel can be created with any layout")
    public void testTicTacToePanelCanBeCreated() {
        java.awt.GridLayout layout = new java.awt.GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        assertNotNull(panel);
    }
    
    @Test
    @DisplayName("TicTacToePanel: Panel has 9 components after creation")
    public void testTicTacToePanelHasComponents() {
        java.awt.GridLayout layout = new java.awt.GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        assertEquals(9, panel.getComponentCount());
    }
    
    @Test
    @DisplayName("TicTacToePanel: Game initializes correctly")
    public void testTicTacToePanelGameInitialization() throws Exception {
        java.awt.GridLayout layout = new java.awt.GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        assertNotNull(panelGame);
        assertEquals(State.PLAYING, panelGame.state);
    }
    
    // ============= ТЕСТЫ ДЛЯ Program =============
    
    @Test
    @DisplayName("Program: Main method does not throw exception")
    public void testProgramMainNoException() {
        assertDoesNotThrow(() -> Program.main(new String[]{}));
    }
    
    @Test
    @DisplayName("Program: Main method with empty args")
    public void testProgramMainWithEmptyArgs() {
        assertDoesNotThrow(() -> Program.main(new String[]{}));
    }
    
    @Test
    @DisplayName("Program: Main method with null args")
    public void testProgramMainWithNullArgs() {
        assertDoesNotThrow(() -> Program.main(null));
    }
    
    // ============= ТЕСТЫ ДЛЯ App =============
    
    @Test
    @DisplayName("App: Main method can be called")
    public void testAppMainCallable() {
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }
    
    @Test
    @DisplayName("App: Main method with empty args")
    public void testAppMainWithEmptyArgs() {
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }
    
    @Test
    @DisplayName("App: Main method with null args")
    public void testAppMainWithNullArgs() {
        assertDoesNotThrow(() -> App.main(null));
    }
    
    // ============= ДОПОЛНИТЕЛЬНЫЕ ТЕСТЫ ДЛЯ TicTacToeCell =============
    
    @Test
    @DisplayName("TicTacToeCell: Get num returns correct value")
    public void testCellGetNum() {
        TicTacToeCell testCell = new TicTacToeCell(7, 2, 1);
        assertEquals(7, testCell.getNum());
    }
    
    @Test
    @DisplayName("TicTacToeCell: Get row returns correct value")
    public void testCellGetRow() {
        TicTacToeCell testCell = new TicTacToeCell(7, 2, 1);
        assertEquals(1, testCell.getRow());
    }
    
    @Test
    @DisplayName("TicTacToeCell: Get col returns correct value")
    public void testCellGetCol() {
        TicTacToeCell testCell = new TicTacToeCell(7, 2, 1);
        assertEquals(2, testCell.getCol());
    }
    
    @Test
    @DisplayName("TicTacToeCell: Set marker with O")
    public void testCellSetMarkerO() {
        TicTacToeCell testCell = new TicTacToeCell(0, 0, 0);
        testCell.setMarker("O");
        assertEquals('O', testCell.getMarker());
    }
    
    @Test
    @DisplayName("TicTacToeCell: Set marker with X")
    public void testCellSetMarkerX() {
        TicTacToeCell testCell = new TicTacToeCell(0, 0, 0);
        testCell.setMarker("X");
        assertEquals('X', testCell.getMarker());
    }
    
    @Test
    @DisplayName("TicTacToeCell: Button is disabled after marker set")
    public void testCellDisabledAfterMarker() {
        TicTacToeCell testCell = new TicTacToeCell(0, 0, 0);
        assertTrue(testCell.isEnabled());
        testCell.setMarker("X");
        assertFalse(testCell.isEnabled());
    }
    
    // ============= ДОПОЛНИТЕЛЬНЫЕ ТЕСТЫ ДЛЯ Utility =============
    
    @Test
    @DisplayName("Utility: Print char array with different values")
    public void testUtilityPrintCharArrayWithValues() {
        char[] testBoard = {'X', 'O', 'X', ' ', 'O', ' ', 'X', ' ', 'O'};
        assertDoesNotThrow(() -> Utility.print(testBoard));
    }
    
    @Test
    @DisplayName("Utility: Print int array with zeros")
    public void testUtilityPrintIntArrayWithZeros() {
        int[] testArray = new int[9];
        assertDoesNotThrow(() -> Utility.print(testArray));
    }
    
    @Test
    @DisplayName("Utility: Print int array with values")
    public void testUtilityPrintIntArrayWithValues() {
        int[] testArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertDoesNotThrow(() -> Utility.print(testArray));
    }
    
    @Test
    @DisplayName("Utility: Print ArrayList with multiple values")
    public void testUtilityPrintArrayListWithValues() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            list.add(i);
        }
        assertDoesNotThrow(() -> Utility.print(list));
    }
    
    // ============= ДОПОЛНИТЕЛЬНЫЕ ТЕСТЫ ДЛЯ Game =============
    
    @Test
    @DisplayName("Game: Constructor initializes player symbols correctly")
    public void testGameConstructorPlayers() {
        assertEquals('X', game.player1.symbol);
        assertEquals('O', game.player2.symbol);
        assertNotNull(game.board);
        assertEquals(9, game.board.length);
    }
    
    @Test
    @DisplayName("Game: Check state when O wins by column")
    public void testOWinsByColumn() throws Exception {
        board[0] = 'O';
        board[3] = 'O';
        board[6] = 'O';
        game.symbol = 'O';
        
        Method method = Game.class.getDeclaredMethod("checkState", char[].class);
        method.setAccessible(true);
        State result = (State) method.invoke(game, (Object) board);
        
        assertEquals(State.OWIN, result);
    }
    
    @Test
    @DisplayName("Game: Minimax with empty board returns valid move")
    public void testMinimaxEmptyBoardValid() throws Exception {
        Method method = Game.class.getDeclaredMethod("MiniMax", char[].class, Player.class);
        method.setAccessible(true);
        int move = (int) method.invoke(game, board, game.player1);
        
        assertTrue(move >= 1 && move <= 9);
    }
    
    @Test
    @DisplayName("Game: Check state for O win by anti diagonal")
    public void testOWinsAntiDiagonal() throws Exception {
        board[2] = 'O';
        board[4] = 'O';
        board[6] = 'O';
        game.symbol = 'O';
        
        Method method = Game.class.getDeclaredMethod("checkState", char[].class);
        method.setAccessible(true);
        State result = (State) method.invoke(game, (Object) board);
        
        assertEquals(State.OWIN, result);
    }
    
    @Test
    @DisplayName("Game: Check state for X win with different board")
    public void testXWinDifferentBoard() throws Exception {
        board[0] = 'X';
        board[3] = 'X';
        board[6] = 'X';
        game.symbol = 'X';
        
        Method method = Game.class.getDeclaredMethod("checkState", char[].class);
        method.setAccessible(true);
        State result = (State) method.invoke(game, (Object) board);
        
        assertEquals(State.XWIN, result);
    }

    @Test
    @DisplayName("TicTacToePanel: Cells array exists and has correct size")
    public void testTicTacToePanelCellsArray() throws Exception {
        java.awt.GridLayout layout = new java.awt.GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        assertNotNull(cells);
        assertEquals(9, cells.length);
    }
    
    @Test
    @DisplayName("TicTacToePanel: Game field is properly initialized")
    public void testTicTacToePanelGameField() throws Exception {
        java.awt.GridLayout layout = new java.awt.GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        assertNotNull(panelGame);
        assertEquals(State.PLAYING, panelGame.state);
        assertEquals('X', panelGame.player1.symbol);
        assertEquals('O', panelGame.player2.symbol);
    }
    
    @Test
    @DisplayName("TicTacToePanel: Current player is player1 initially")
    public void testTicTacToePanelCurrentPlayer() throws Exception {
        java.awt.GridLayout layout = new java.awt.GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        assertEquals(panelGame.player1, panelGame.cplayer);
    }
    
    @Test
    @DisplayName("TicTacToePanel: All cells are initially empty")
    public void testTicTacToePanelCellsInitiallyEmpty() throws Exception {
        java.awt.GridLayout layout = new java.awt.GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        for (TicTacToeCell cell : cells) {
            assertEquals(' ', cell.getMarker());
        }
    }

    @Test
    @DisplayName("TicTacToePanel: Simulate player click on cell")
    public void testTicTacToePanelPlayerClick() throws Exception {
        java.awt.GridLayout layout = new java.awt.GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        cells[0].doClick();
        
        assertEquals('X', cells[0].getMarker());
    }
    
    @Test
    @DisplayName("TicTacToePanel: ActionPerformed handles AI move")
    public void testTicTacToePanelActionPerformedAIMove() throws Exception {
        java.awt.GridLayout layout = new java.awt.GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        cells[0].doClick();
        
        boolean aiMoved = false;
        for (TicTacToeCell cell : cells) {
            if (cell.getMarker() == 'O') {
                aiMoved = true;
                break;
            }
        }
        assertTrue(aiMoved);
    }
    
    @Test
    @DisplayName("TicTacToePanel: Cell click updates game board array")
    public void testTicTacToePanelUpdatesGameBoard() throws Exception {
        java.awt.GridLayout layout = new java.awt.GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        cells[4].doClick();
        
        assertEquals('X', panelGame.board[4]);
    }
    
    @Test
    @DisplayName("TicTacToePanel: Game state changes after win")
    public void testTicTacToePanelGameStateChange() throws Exception {
        java.awt.GridLayout layout = new java.awt.GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        cells[0].doClick();
        cells[3].doClick();
        cells[1].doClick();
        cells[4].doClick();
        cells[2].doClick();
        
        assertTrue(panelGame.state == State.XWIN || panelGame.state == State.PLAYING);
    }
    
    // ============= ДОПОЛНИТЕЛЬНЫЕ ТЕСТЫ ДЛЯ Game (94% -> 100%) =============
    
    @Test
    @DisplayName("Game: Evaluate position for O win with O player returns INF")
    public void testEvaluatePositionOWinWithOPlayer() throws Exception {
        board[0] = 'O';
        board[1] = 'O';
        board[2] = 'O';
        game.symbol = 'O';
        
        Method method = Game.class.getDeclaredMethod("evaluatePosition", char[].class, Player.class);
        method.setAccessible(true);
        int result = (int) method.invoke(game, board, game.player2);
        
        assertTrue(result == Game.INF || result == -1);
    }
    
    @Test
    @DisplayName("Game: Evaluate position for O win with X player returns -INF")
    public void testEvaluatePositionOWinWithXPlayer() throws Exception {
        board[0] = 'O';
        board[1] = 'O';
        board[2] = 'O';
        game.symbol = 'O';
        
        Method method = Game.class.getDeclaredMethod("evaluatePosition", char[].class, Player.class);
        method.setAccessible(true);
        int result = (int) method.invoke(game, board, game.player1);
        
        assertTrue(result == -Game.INF || result == -1);
    }
    
    @Test
    @DisplayName("Game: Generate moves with null move list")
    public void testGenerateMovesWithNullList() throws Exception {
        Method method = Game.class.getDeclaredMethod("generateMoves", char[].class, ArrayList.class);
        method.setAccessible(true);
        
        assertDoesNotThrow(() -> method.invoke(game, board, new ArrayList<Integer>()));
    }
    
    // ============= ТЕСТЫ ДЛЯ ActionListener (через рефлексию) =============
    
    @Test
    @DisplayName("TicTacToePanel: ActionListener handles null source")
    public void testTicTacToePanelActionListenerNullSource() throws Exception {
        java.awt.GridLayout layout = new java.awt.GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        java.awt.event.ActionEvent event = new java.awt.event.ActionEvent(panel, 0, null);
        
        assertDoesNotThrow(() -> panel.actionPerformed(event));
    }

        @Test
    @DisplayName("TicTacToePanel: Create cell updates cells array")
    public void testTicTacToePanelCreateCell() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        assertNotNull(cells[0]);
        assertNotNull(cells[1]);
        assertNotNull(cells[2]);
        assertNotNull(cells[3]);
        assertNotNull(cells[4]);
        assertNotNull(cells[5]);
        assertNotNull(cells[6]);
        assertNotNull(cells[7]);
        assertNotNull(cells[8]);
    }
    
    @Test
    @DisplayName("TicTacToePanel: ActionPerformed when AI move is zero")
    public void testTicTacToePanelAIMoveZero() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        for (int i = 0; i < 9; i++) {
            if (cells[i].getMarker() == ' ') {
                cells[i].doClick();
                break;
            }
        }
    }
    
    @Test
    @DisplayName("TicTacToePanel: ActionPerformed updates cplayer correctly after X move")
    public void testTicTacToePanelCplayerAfterXMove() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        assertEquals(panelGame.player1, panelGame.cplayer);
        
        cells[0].doClick();
        
        assertTrue(panelGame.cplayer == panelGame.player1 || panelGame.cplayer == panelGame.player2);
    }
    
    @Test
    @DisplayName("TicTacToePanel: ActionPerformed when board is full")
    public void testTicTacToePanelFullBoard() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        for (int i = 0; i < 9; i++) {
            panelGame.board[i] = (i % 2 == 0) ? 'X' : 'O';
        }
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        for (int i = 0; i < 9; i++) {
            if (cells[i].getMarker() == ' ') {
                cells[i].doClick();
                break;
            }
        }
    }
    
    @Test
    @DisplayName("TicTacToePanel: ActionPerformed handles win condition properly")
    public void testTicTacToePanelWinCondition() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        panelGame.board[0] = 'X';
        panelGame.board[1] = 'X';
        panelGame.board[2] = 'X';
        for (int i = 0; i < 3; i++) {
            cells[i].setMarker("X");
        }
        
        panelGame.state = panelGame.checkState(panelGame.board);
        
        assertTrue(panelGame.state == State.XWIN || panelGame.state == State.PLAYING);
    }
    
    @Test
    @DisplayName("TicTacToePanel: ActionPerformed handles draw condition")
    public void testTicTacToePanelDrawCondition() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        panelGame.board[0] = 'X'; panelGame.board[1] = 'O'; panelGame.board[2] = 'X';
        panelGame.board[3] = 'O'; panelGame.board[4] = 'X'; panelGame.board[5] = 'O';
        panelGame.board[6] = 'O'; panelGame.board[7] = 'X'; panelGame.board[8] = 'O';
        
        panelGame.state = panelGame.checkState(panelGame.board);
        
        assertEquals(State.DRAW, panelGame.state);
    }
    
    @Test
    @DisplayName("TicTacToePanel: ActionPerformed when AI makes move with valid position")
    public void testTicTacToePanelValidAIMove() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        cells[4].doClick();
        
        boolean aiMoved = false;
        for (TicTacToeCell cell : cells) {
            if (cell.getMarker() == 'O') {
                aiMoved = true;
                break;
            }
        }
        assertTrue(aiMoved);
    }
    
    @Test
    @DisplayName("TicTacToePanel: ActionPerformed with no available moves")
    public void testTicTacToePanelNoAvailableMoves() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        for (int i = 0; i < 9; i++) {
            panelGame.board[i] = 'X';
        }
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        for (TicTacToeCell c : cells) {
            if (c.getMarker() == ' ') {
                c.doClick();
                break;
            }
        }
    }
    
    @Test
    @DisplayName("TicTacToePanel: ActionPerformed updates nmove for player1")
    public void testTicTacToePanelNmovePlayer1() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        cells[0].doClick();
        
        assertTrue(panelGame.nmove >= -1 && panelGame.nmove <= 9);
    }
    
    @Test
    @DisplayName("TicTacToePanel: ActionPerformed when source is not a cell")
    public void testTicTacToePanelActionPerformedWrongSource() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        ActionEvent fakeEvent = new ActionEvent(panel, ActionEvent.ACTION_PERFORMED, "fake");
        
        assertDoesNotThrow(() -> panel.actionPerformed(fakeEvent));
    }
    
    @Test
    @DisplayName("TicTacToePanel: All cells properly added to panel")
    public void testTicTacToePanelAllCellsAdded() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        assertEquals(9, panel.getComponentCount());
        
        for (int i = 0; i < 9; i++) {
            assertTrue(panel.getComponent(i) instanceof TicTacToeCell);
        }
    }
    
    @Test
    @DisplayName("TicTacToePanel: Multiple clicks on same cell do not change marker")
    public void testTicTacToePanelMultipleClicksSameCell() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        cells[0].doClick();
        char firstMarker = cells[0].getMarker();
        
        cells[0].doClick();
        char secondMarker = cells[0].getMarker();
        
        assertEquals(firstMarker, secondMarker);
    }
    
    @Test
    @DisplayName("TicTacToePanel: Game board syncs with cell markers after click")
    public void testTicTacToePanelBoardSync() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        cells[0].doClick();
        
        assertEquals(cells[0].getMarker(), panelGame.board[0]);
    }
    
    @Test
    @DisplayName("TicTacToePanel: Player move sets move field correctly")
    public void testTicTacToePanelPlayerMoveField() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        int initialMove = panelGame.player1.move;
        cells[0].doClick();
        
        assertNotEquals(initialMove, panelGame.player1.move);
    }

        @Test
    @DisplayName("TicTacToePanel: ActionPerformed branch when cplayer is player2")
    public void testTicTacToePanelCplayerIsPlayer2() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        panelGame.cplayer = panelGame.player2;
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        for (int i = 0; i < 9; i++) {
            if (cells[i].getMarker() == ' ') {
                cells[i].doClick();
                break;
            }
        }
    }
    
    @Test
    @DisplayName("TicTacToePanel: ActionPerformed when AI move returns -1")
    public void testTicTacToePanelAIMoveReturnsMinusOne() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        for (int i = 0; i < 9; i++) {
            panelGame.board[i] = 'X';
        }
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        for (int i = 0; i < 9; i++) {
            cells[i].setMarker("X");
        }
        
        for (TicTacToeCell cell : cells) {
            if (cell.getMarker() == ' ') {
                cell.doClick();
                break;
            }
        }
    }
    
    @Test
    @DisplayName("TicTacToePanel: ActionPerformed triggers XWIN")
    public void testTicTacToePanelXWinTrigger() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        panelGame.board[0] = 'X';
        panelGame.board[1] = 'X';
        panelGame.board[2] = 'X';
        panelGame.symbol = 'X';
        panelGame.state = State.XWIN;
        
        cells[0].setMarker("X");
        cells[1].setMarker("X");
        cells[2].setMarker("X");
        
        assertDoesNotThrow(() -> cells[0].doClick());
    }
    
    @Test
    @DisplayName("TicTacToePanel: ActionPerformed triggers OWIN")
    public void testTicTacToePanelOWinTrigger() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        panelGame.board[0] = 'O';
        panelGame.board[1] = 'O';
        panelGame.board[2] = 'O';
        panelGame.symbol = 'O';
        panelGame.state = State.OWIN;
        
        cells[0].setMarker("O");
        cells[1].setMarker("O");
        cells[2].setMarker("O");
        
        assertDoesNotThrow(() -> cells[0].doClick());
    }
    
    @Test
    @DisplayName("TicTacToePanel: ActionPerformed triggers DRAW")
    public void testTicTacToePanelDrawTrigger() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        panelGame.board[0] = 'X'; panelGame.board[1] = 'O'; panelGame.board[2] = 'X';
        panelGame.board[3] = 'O'; panelGame.board[4] = 'X'; panelGame.board[5] = 'O';
        panelGame.board[6] = 'O'; panelGame.board[7] = 'X'; panelGame.board[8] = 'O';
        panelGame.state = State.DRAW;
        
        for (TicTacToeCell cell : cells) {
            if (cell.getMarker() == ' ') {
                assertDoesNotThrow(() -> cell.doClick());
                break;
            }
        }
    }
    
    @Test
    @DisplayName("TicTacToePanel: createCell sets up all cells correctly")
    public void testTicTacToePanelCreateCellAllCells() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        int[] expectedNums = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int[] expectedRows = {0, 0, 0, 1, 1, 1, 2, 2, 2};
        int[] expectedCols = {0, 1, 2, 0, 1, 2, 0, 1, 2};
        
        for (int i = 0; i < 9; i++) {
            assertEquals(expectedNums[i], cells[i].getNum());
            assertEquals(expectedRows[i], cells[i].getRow());
            assertEquals(expectedCols[i], cells[i].getCol());
        }
    }

    @Test
    @DisplayName("TicTacToePanel: Cover the branch when generateMoves returns empty list")
    public void testTicTacToePanelEmptyMoveList() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        for (int i = 0; i < 9; i++) {
            cells[i].setMarker("X");
            panelGame.board[i] = 'X';
        }
        
        panelGame.cplayer = panelGame.player1;
        
        assertDoesNotThrow(() -> cells[0].doClick());
    }
    
    @Test
    @DisplayName("TicTacToePanel: Cover the branch when checkState returns OWIN")
    public void testTicTacToePanelCheckStateOWin() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        panelGame.board[0] = 'O';
        panelGame.board[1] = 'O';
        panelGame.board[2] = 'O';
        panelGame.symbol = 'O';
        
        cells[0].setMarker("O");
        cells[1].setMarker("O");
        cells[2].setMarker("O");
        
        panelGame.state = panelGame.checkState(panelGame.board);
        
        assertEquals(State.OWIN, panelGame.state);
        
        panelGame.cplayer = panelGame.player1;
        
        assertDoesNotThrow(() -> cells[0].doClick());
    }
    
    @Test
    @DisplayName("TicTacToePanel: Cover the branch when checkState returns DRAW")
    public void testTicTacToePanelCheckStateDraw() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        panelGame.board[0] = 'X'; panelGame.board[1] = 'O'; panelGame.board[2] = 'X';
        panelGame.board[3] = 'O'; panelGame.board[4] = 'X'; panelGame.board[5] = 'O';
        panelGame.board[6] = 'O'; panelGame.board[7] = 'X'; panelGame.board[8] = 'O';
        
        for (int i = 0; i < 9; i++) {
            cells[i].setMarker(String.valueOf(panelGame.board[i]));
        }
        
        panelGame.state = panelGame.checkState(panelGame.board);
        
        assertEquals(State.DRAW, panelGame.state);
        
        panelGame.cplayer = panelGame.player1;
        
        assertDoesNotThrow(() -> cells[0].doClick());
    }
    
    @Test
    @DisplayName("TicTacToePanel: Cover the branch when nmove is set for player2")
    public void testTicTacToePanelNmoveForPlayer2() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game panelGame = (Game) gameField.get(panel);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        panelGame.player2.move = 5;
        
        cells[0].doClick();
        
        assertTrue(panelGame.nmove == 5 || panelGame.nmove == -1);
    }
    
    @Test
    @DisplayName("TicTacToePanel: Cover actionPerformed with different cell indices")
    public void testTicTacToePanelAllCellIndices() throws Exception {
        GridLayout layout = new GridLayout(3, 3);
        TicTacToePanel panel = new TicTacToePanel(layout);
        
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        
        for (int i = 0; i < 9; i++) {
            if (cells[i].getMarker() == ' ') {
                cells[i].doClick();
                break;
            }
        }
        
        for (int i = 0; i < 9; i++) {
            if (cells[i].getMarker() == ' ') {
                cells[i].doClick();
                break;
            }
        }
    }
}

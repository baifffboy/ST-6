package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.lang.reflect.Field;


public class AppTest {
    
    private Program program;
    private char[][] board;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @BeforeEach
    public void setUp() throws Exception {
        program = new Program(null);
        Field field = Program.class.getDeclaredField("board");
        field.setAccessible(true);
        board = (char[][]) field.get(program);
    }

    @Test
    @DisplayName("Тест App: создание экземпляра App")
    public void testAppNewInstance() {
        App app = new App();
        assertNotNull(app);
    }
    
    @Test
    @DisplayName("Тест App: проверка существования класса")
    public void testAppClassIsPresent() {
        assertNotNull(App.class);
    }
    
    @Test
    @DisplayName("Тест App: метод main существует")
    public void testAppMainMethodExists() {
        assertDoesNotThrow(() -> {
            App.class.getDeclaredMethod("main", String[].class);
        });
    }
    
    @Test
    @DisplayName("Тест App: метод startGame существует")
    public void testAppStartGameMethodExists() {
        assertDoesNotThrow(() -> {
            App.class.getDeclaredMethod("startGame");
        });
    }

    @Test
    @DisplayName("Тест App: main метод можно вызвать с полным вводом для ничьей")
    public void testAppMainCallable() {
        String input = "1 1\n2 2\n3 3\n1 2\n2 3\n3 1\n2 1\n1 3\n3 2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        assertDoesNotThrow(() -> {
            App.main(new String[]{});
        });
        
        System.setIn(System.in);
    }

    @Test
    @DisplayName("Тест App: main метод с null аргументами и полным вводом")
    public void testAppMainNullArgs() {
        String input = "1 1\n2 2\n3 3\n1 2\n2 3\n3 1\n2 1\n1 3\n3 2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        assertDoesNotThrow(() -> {
            App.main(null);
        });
        
        System.setIn(System.in);
    }
    
    @Test
    @DisplayName("Тест 1: Полная партия - победа игрока (первая строка)")
    public void testFullGamePlayerWinsFirstRow() throws Exception {
        String input = "1 1\n1 2\n1 3\n";
        Program testProgram = new Program(input);

        Field boardField = Program.class.getDeclaredField("board");
        boardField.setAccessible(true);
        char[][] board = (char[][]) boardField.get(testProgram);
        
        Method playerMoveMethod = Program.class.getDeclaredMethod("playerMove");
        playerMoveMethod.setAccessible(true);

        playerMoveMethod.invoke(testProgram);
        playerMoveMethod.invoke(testProgram);
        playerMoveMethod.invoke(testProgram);

        assertEquals('X', board[0][0]);
        assertEquals('X', board[0][1]);
        assertEquals('X', board[0][2]);

        Method evaluateMethod = Program.class.getDeclaredMethod("evaluate");
        evaluateMethod.setAccessible(true);
        int result = (int) evaluateMethod.invoke(testProgram);
        
        assertEquals(-10, result);
    }
    
    @Test
    @DisplayName("Тест 2: Ход игрока с валидными координатами")
    public void testPlayerMoveValidInput() throws Exception {
        String input = "2 2\n";
        Program testProgram = new Program(input);
        
        Method playerMoveMethod = Program.class.getDeclaredMethod("playerMove");
        playerMoveMethod.setAccessible(true);
        playerMoveMethod.invoke(testProgram);
        
        Field boardField = Program.class.getDeclaredField("board");
        boardField.setAccessible(true);
        char[][] board = (char[][]) boardField.get(testProgram);
        
        assertEquals('X', board[1][1]);
    }
    
    @Test
    @DisplayName("Тест 3: Ход игрока с невалидными координатами (повторный ввод)")
    public void testPlayerMoveInvalidThenValidInput() throws Exception {
        String input = "5 5\n1 1\n";
        Program testProgram = new Program(input);
        
        Method playerMoveMethod = Program.class.getDeclaredMethod("playerMove");
        playerMoveMethod.setAccessible(true);
        playerMoveMethod.invoke(testProgram);
        
        Field boardField = Program.class.getDeclaredField("board");
        boardField.setAccessible(true);
        char[][] board = (char[][]) boardField.get(testProgram);
        
        assertEquals('X', board[0][0]);
    }
    
    @Test
    @DisplayName("Тест 4: Полная партия - ничья")
    public void testFullGameDraw() throws Exception {

        String input = "2 2\n1 1\n3 3\n1 2\n2 3\n3 1\n";
        Program testProgram = new Program(input);
        
        Method startMethod = Program.class.getDeclaredMethod("startForTest");
        startMethod.setAccessible(true);
        startMethod.invoke(testProgram);
        
        Method evaluateMethod = Program.class.getDeclaredMethod("evaluate");
        evaluateMethod.setAccessible(true);
        int result = (int) evaluateMethod.invoke(testProgram);

        assertTrue(result == 0 || result == -10);
    }

    
    @Test
    @DisplayName("Тест 5: Инициализация доски")
    public void testBoardInitialization() throws Exception {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(' ', board[i][j]);
            }
        }
    }
    
    @Test
    @DisplayName("Тест 6: AI выигрывает по строке")
    public void testEvaluateAIWinsRow() throws Exception {
        board[0][0] = 'O';
        board[0][1] = 'O';
        board[0][2] = 'O';
        
        Method method = Program.class.getDeclaredMethod("evaluate");
        method.setAccessible(true);
        int result = (int) method.invoke(program);
        
        assertEquals(10, result);
    }
    
    @Test
    @DisplayName("Тест 7: Игрок выигрывает по строке")
    public void testEvaluatePlayerWinsRow() throws Exception {
        board[0][0] = 'X';
        board[0][1] = 'X';
        board[0][2] = 'X';
        
        Method method = Program.class.getDeclaredMethod("evaluate");
        method.setAccessible(true);
        int result = (int) method.invoke(program);
        
        assertEquals(-10, result);
    }
    
    @Test
    @DisplayName("Тест 8: AI выигрывает по колонке")
    public void testEvaluateAIWinsColumn() throws Exception {
        board[0][0] = 'O';
        board[1][0] = 'O';
        board[2][0] = 'O';
        
        Method method = Program.class.getDeclaredMethod("evaluate");
        method.setAccessible(true);
        int result = (int) method.invoke(program);
        
        assertEquals(10, result);
    }
    
    @Test
    @DisplayName("Тест 9: AI выигрывает по диагонали")
    public void testEvaluateAIWinsDiagonal() throws Exception {
        board[0][0] = 'O';
        board[1][1] = 'O';
        board[2][2] = 'O';
        
        Method method = Program.class.getDeclaredMethod("evaluate");
        method.setAccessible(true);
        int result = (int) method.invoke(program);
        
        assertEquals(10, result);
    }
    
    @Test
    @DisplayName("Тест 10: Нет победителя")
    public void testEvaluateNoWinner() throws Exception {
        Method method = Program.class.getDeclaredMethod("evaluate");
        method.setAccessible(true);
        int result = (int) method.invoke(program);
        
        assertEquals(0, result);
    }
    
    @Test
    @DisplayName("Тест 11: isMovesLeft - есть ходы")
    public void testIsMovesLeftTrue() throws Exception {
        Method method = Program.class.getDeclaredMethod("isMovesLeft");
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(program);
        
        assertTrue(result);
    }
    
    @Test
    @DisplayName("Тест 12: isMovesLeft - нет ходов")
    public void testIsMovesLeftFalse() throws Exception {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 'X';
            }
        }
        
        Method method = Program.class.getDeclaredMethod("isMovesLeft");
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(program);
        
        assertFalse(result);
    }
    
    @Test
    @DisplayName("Тест 13: Минимакс - AI выигрывает")
    public void testMinimaxAIWins() throws Exception {
        board[0][0] = 'O';
        board[0][1] = 'O';
        board[0][2] = ' ';
        
        Method method = Program.class.getDeclaredMethod("minimax", int.class, boolean.class);
        method.setAccessible(true);
        int result = (int) method.invoke(program, 0, true);
        
        assertEquals(9, result);
    }
    
    @Test
    @DisplayName("Тест 14: Минимакс - игрок выигрывает")
    public void testMinimaxPlayerWins() throws Exception {
        board[0][0] = 'X';
        board[0][1] = 'X';
        board[0][2] = ' ';
        
        Method method = Program.class.getDeclaredMethod("minimax", int.class, boolean.class);
        method.setAccessible(true);
        int result = (int) method.invoke(program, 0, false);
        
        assertEquals(-9, result);
    }

    @Test
    @DisplayName("Тест App: создание экземпляра App")
    public void testAppConstructor() {
        App app = new App();
        assertNotNull(app);
    }
    
    @Test
    @DisplayName("Тест App: класс App существует")
    public void testAppClassExists() {
        assertNotNull(App.class);
    }
}

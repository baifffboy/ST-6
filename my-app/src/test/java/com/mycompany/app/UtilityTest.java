package com.mycompany.app;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    @Test
    void testPrintCharArray() {
        char[] board = {'X', 'O', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        Utility.print(board);
        
        String output = outContent.toString();
        assertTrue(output.contains("X-O-X"));
        
        System.setOut(System.out);
    }

    @Test
    void testPrintIntArray() {
        int[] board = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        Utility.print(board);
        
        String output = outContent.toString();
        assertTrue(output.contains("1-2-3-4-5-6-7-8-9"));
        
        System.setOut(System.out);
    }

    @Test
    void testPrintArrayList() {
        ArrayList<Integer> moves = new ArrayList<>();
        moves.add(0);
        moves.add(3);
        moves.add(5);
        moves.add(8);
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        Utility.print(moves);
        
        String output = outContent.toString();
        assertTrue(output.contains("0-3-5-8"));
        
        System.setOut(System.out);
    }

    @Test
    void testPrintEmptyArrayList() {
        ArrayList<Integer> moves = new ArrayList<>();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        Utility.print(moves);
        
        String output = outContent.toString();
        assertEquals("\n\n", output);
        
        System.setOut(System.out);
    }

    @Test
    void testPrintEmptyCharArray() {
        char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        Utility.print(board);
        
        String output = outContent.toString();
        assertTrue(output.contains("-"));
        
        System.setOut(System.out);
    }

    @Test
    void testPrintIntArrayWithDifferentValues() {
        int[] board = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        Utility.print(board);
        
        String output = outContent.toString();
        assertTrue(output.contains("0-0-0-0-0-0-0-0-0"));
        
        System.setOut(System.out);
    }
}

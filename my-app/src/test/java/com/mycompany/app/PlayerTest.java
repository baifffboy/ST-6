package com.mycompany.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPlayerInitialization() {
        Player player = new Player();
        assertEquals('\u0000', player.symbol);
        assertEquals(0, player.move);
        assertFalse(player.selected);
        assertFalse(player.win);
    }

    @Test
    void testPlayerSymbolAssignment() {
        Player player = new Player();
        player.symbol = 'X';
        assertEquals('X', player.symbol);
        
        player.symbol = 'O';
        assertEquals('O', player.symbol);
    }

    @Test
    void testPlayerMoveAssignment() {
        Player player = new Player();
        player.move = 5;
        assertEquals(5, player.move);
        
        player.move = -1;
        assertEquals(-1, player.move);
    }
}

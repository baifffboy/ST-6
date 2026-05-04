package com.mycompany.app;

import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToePanelTest {

    @Test
    void testPanelCreation() {
        TicTacToePanel panel = new TicTacToePanel(new GridLayout(3, 3));
        assertNotNull(panel);
        assertEquals(9, panel.getComponentCount());
    }

    @Test
    void testPanelHasButtons() {
        TicTacToePanel panel = new TicTacToePanel(new GridLayout(3, 3));
        int buttonCount = 0;
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JButton) {
                buttonCount++;
            }
        }
        assertEquals(9, buttonCount);
    }
}

package com.mycompany.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StateTest {

    @Test
    void testStateEnumValues() {
        State[] states = State.values();
        assertEquals(4, states.length);
    }

    @Test
    void testStateEnumNames() {
        assertEquals("PLAYING", State.PLAYING.name());
        assertEquals("OWIN", State.OWIN.name());
        assertEquals("XWIN", State.XWIN.name());
        assertEquals("DRAW", State.DRAW.name());
    }
}

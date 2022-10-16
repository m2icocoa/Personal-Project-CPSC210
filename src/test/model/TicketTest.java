package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TicketTest {
    protected Ticket testTicket;

    @BeforeEach
    public void runBefore() {
        testTicket = new Ticket("upper", 200, 1, 10, 50.5);
    }

    @Test
    public void testConstructor() {
        assertEquals("upper", testTicket.getLevel());
        assertEquals(200, testTicket.getSection());
        assertEquals(1, testTicket.getRow());
        assertEquals(10, testTicket.getNumber());
        assertEquals(50.5, testTicket.getPrice());
    }
}
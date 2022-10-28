package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TicketTest {
    protected Ticket testTicket;

    @BeforeEach
    public void runBefore() {
        testTicket =
                new Ticket("upper", 200, 1, 10, 50.5);
    }

    @Test
    public void testConstructor() {
        assertEquals("upper", testTicket.getLevel());
        assertEquals(200, testTicket.getSection());
        assertEquals(1, testTicket.getRow());
        assertEquals(10, testTicket.getNumber());
        assertEquals(50.5, testTicket.getPrice());
    }

    @Test
    public void testAssignLevel() {
        testTicket.assignLevel("upper");
        assertEquals("upper", testTicket.getLevel());
    }

    @Test
    public void testAssignSection() {
        testTicket.assignSection(201);
        assertEquals(201, testTicket.getSection());
    }

    @Test
    public void testAssignRow() {
        testTicket.assignRow(10);
        assertEquals(10, testTicket.getRow());
    }

    @Test
    public void testAssignNumber() {
        testTicket.assignNumber(20);
        assertEquals(20, testTicket.getNumber());
    }

    @Test
    public void testAssignPriceIfLower() {
        testTicket.assignPrice("lower", 10);
        assertEquals(100.0 + 2.0 * (23 + 1 - 10), testTicket.getPrice());
    }

    @Test
    public void testAssignPriceIfUpper() {
        testTicket.assignPrice("upper", 10);
        assertEquals(80.0 + 1.5 * (23 + 1 - 10), testTicket.getPrice());
    }

    @Test
    public void testAssignPriceInvalid() {
        testTicket.assignPrice("hello", 10);
        assertEquals(0.0, testTicket.getPrice());
    }

    @Test
    public void testToString() {
        assertEquals("Level: upper, Section: 200, Row: 1, Number: 10, Price: $50.5", testTicket.toString());
    }
}
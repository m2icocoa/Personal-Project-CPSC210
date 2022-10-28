package persistence;

import model.Ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTicket(String level, int section, int row, int number, double price, Ticket ticket) {
        assertEquals(level, ticket.getLevel());
        assertEquals(section, ticket.getSection());
        assertEquals(row, ticket.getRow());
        assertEquals(number, ticket.getNumber());
        assertEquals(price, ticket.getPrice());
    }
}


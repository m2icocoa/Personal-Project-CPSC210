package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestMyCart {
    private MyCart testMyCart;
    private Ticket firstTicket;
    private Ticket secondTicket;
    private Ticket thirdTicket;

    @BeforeEach
    public void runBefore() {
        testMyCart = new MyCart();
        firstTicket =
                new Ticket("lower", 100, 1, 1, 120.0);
        secondTicket =
                new Ticket("upper", 201, 15, 9, 80.8);
        thirdTicket  =
                new Ticket("upper", 210, 20, 20, 50.7);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testMyCart.quantity());
    }

    @Test
    public void testAddOneTicket() {
        checkMyCartDoesNotContainTicket(firstTicket);
        testMyCart.addTicket(firstTicket);
        checkMyCartContainsTicket(firstTicket);
    }

    @Test
    public void testAddSameTicketMultipleTimes() {
        checkMyCartDoesNotContainTicket(firstTicket);
        testMyCart.addTicket(firstTicket);
        checkMyCartContainsTicket(firstTicket);
        testMyCart.addTicket(firstTicket);
        checkMyCartContainsTicket(firstTicket);
    }

    @Test
    public void testAddDifferentTickets() {
        checkMyCartDoesNotContainTicket(firstTicket);
        testMyCart.addTicket(firstTicket);
        checkMyCartDoesNotContainTicket(secondTicket);
        testMyCart.addTicket(secondTicket);
    }

    @Test
    public void testRemoveOneTicket() {
        testMyCart.addTicket(firstTicket);
        checkMyCartContainsTicket(firstTicket);
        testMyCart.remove(firstTicket);
        checkMyCartDoesNotContainTicket(firstTicket);
    }

    @Test
    public void testRemoveDifferentTickets() {
        testMyCart.addTicket(firstTicket);
        testMyCart.addTicket(secondTicket);
        checkMyCartContainsTicket(firstTicket);
        testMyCart.remove(firstTicket);
        checkMyCartDoesNotContainTicket(firstTicket);
        checkMyCartContainsTicket(secondTicket);
        testMyCart.remove(secondTicket);
        checkMyCartDoesNotContainTicket(secondTicket);
    }

    @Test
    public void testRemoveOneTicketButNotThere() {
        testMyCart.addTicket(firstTicket);
        checkMyCartDoesNotContainTicket(secondTicket);
        testMyCart.remove(secondTicket);
    }

    @Test
    public void testGetTicket() {
        testMyCart.addTicket(firstTicket);
        assertEquals(firstTicket, testMyCart.get(0));
    }

    @Test
    private void checkMyCartDoesNotContainTicket(Ticket ticket) {
        assertFalse(testMyCart.contains(ticket));
    }

    @Test
    private void checkMyCartContainsTicket(Ticket ticket) {
        assertTrue(testMyCart.quantity() > 0);
        assertTrue(testMyCart.contains(ticket));
    }

    @Test
    public void testQuantity() {
        testMyCart.addTicket(firstTicket);
        testMyCart.addTicket(secondTicket);
        testMyCart.addTicket(thirdTicket);
        assertEquals(3, testMyCart.quantity());
    }

    @Test
    public void testLevel() {
        testMyCart.addTicket(firstTicket);
        assertEquals("lower", testMyCart.level(0));
    }

    @Test
    public void testSection() {
        testMyCart.addTicket(firstTicket);
        assertEquals(100, testMyCart.section(0));
    }

    @Test
    public void testRow() {
        testMyCart.addTicket(firstTicket);
        assertEquals(1, testMyCart.row(0));
    }

    @Test
    public void testNumber() {
        testMyCart.addTicket(firstTicket);
        assertEquals(1, testMyCart.number(0));
    }

    @Test
    public void testPrice() {
        testMyCart.addTicket(firstTicket);
        assertEquals(120.0, testMyCart.price(0));
    }

    @Test
    public void testTotalPriceOneTicket() {
        testMyCart.addTicket(firstTicket);
        assertEquals(120.0, testMyCart.totalPrice());
    }

    @Test
    public void testTotalPriceMultipleTickets() {
        testMyCart.addTicket(firstTicket);
        testMyCart.addTicket(secondTicket);
        testMyCart.addTicket(thirdTicket);
        assertEquals(251.5, testMyCart.totalPrice());
    }

    @Test
    public void testTotalPriceEmptyCart() {
        assertEquals(0, testMyCart.quantity());
        assertEquals(0.0, testMyCart.totalPrice());
    }
}

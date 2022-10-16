package model;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class TestMyCart {
    private MyCart testMyCart;
    private Ticket firstTicket;
    private Ticket secondTicket;
    private Ticket thirdTicket;

    @Before
    public void setup() {
        testMyCart = new MyCart();
    }

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
        testMyCart.add(firstTicket);
        checkMyCartContainsTicket(firstTicket);
    }

    @Test
    public void testAddSameTicketMultipleTimes() {
        checkMyCartDoesNotContainTicket(firstTicket);
        testMyCart.add(firstTicket);
        checkMyCartContainsTicket(firstTicket);
        testMyCart.add(firstTicket);
        checkMyCartContainsTicket(firstTicket);
    }

    @Test
    public void testAddDifferentTickets() {
        checkMyCartDoesNotContainTicket(firstTicket);
        testMyCart.add(firstTicket);
        checkMyCartDoesNotContainTicket(secondTicket);
        testMyCart.add(secondTicket);
    }

    @Test
    public void testRemoveOneTicket() {
        testMyCart.add(firstTicket);
        checkMyCartContainsTicket(firstTicket);
        testMyCart.remove(firstTicket);
        checkMyCartDoesNotContainTicket(firstTicket);
    }

    @Test
    public void testRemoveDifferentTickets() {
        testMyCart.add(firstTicket);
        testMyCart.add(secondTicket);
        checkMyCartContainsTicket(firstTicket);
        testMyCart.remove(firstTicket);
        checkMyCartDoesNotContainTicket(firstTicket);
        checkMyCartContainsTicket(secondTicket);
        testMyCart.remove(secondTicket);
        checkMyCartDoesNotContainTicket(secondTicket);
    }

    @Test
    public void testGetTicket() {
        testMyCart.add(firstTicket);
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
        testMyCart.add(firstTicket);
        testMyCart.add(secondTicket);
        testMyCart.add(thirdTicket);
        assertEquals(3, testMyCart.quantity());
    }

    @Test
    public void testLevel() {
        testMyCart.add(firstTicket);
        assertEquals("lower", testMyCart.get(0).getLevel());
    }

    @Test
    public void testSection() {
        testMyCart.add(firstTicket);
        assertEquals(100, testMyCart.get(0).getSection());
    }

    @Test
    public void testRow() {
        testMyCart.add(firstTicket);
        assertEquals(1, testMyCart.get(0).getRow());
    }

    @Test
    public void testNumber() {
        testMyCart.add(firstTicket);
        assertEquals(1, testMyCart.get(0).getRow());
    }

    @Test
    public void testPrice() {
        testMyCart.add(firstTicket);
        assertEquals(120.0, testMyCart.get(0).getPrice());
    }

    @Test
    public void testTotalPriceOneTicket() {
        testMyCart.add(firstTicket);
        assertEquals(120.0, testMyCart.totalPrice());
    }

    @Test
    public void testTotalPriceMultipleTickets() {
        testMyCart.add(firstTicket);
        testMyCart.add(secondTicket);
        testMyCart.add(thirdTicket);
        assertEquals(251.5, testMyCart.totalPrice());
    }

    @Test
    public void testTotalPriceEmptyCart() {
        assertEquals(0, testMyCart.quantity());
        assertEquals(0.0, testMyCart.totalPrice());
    }
}

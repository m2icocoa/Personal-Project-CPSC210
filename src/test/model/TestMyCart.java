package model;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void testAddNewTicket() {
        checkMyCartDoesNotContainTicket(firstTicket);
        testMyCart.add(firstTicket);
        checkMyCartContainsTicket(firstTicket);
    }

    @Test
    public void testAddSameTicket() {
        checkMyCartDoesNotContainTicket(firstTicket);
        testMyCart.add(firstTicket);
        checkMyCartContainsTicket(firstTicket);
        testMyCart.add(firstTicket);
        checkMyCartContainsTicket(firstTicket);
    }

    @Test
    public void testRemoveTicket() {
        testMyCart.add(firstTicket);
        testMyCart.remove(firstTicket);
        checkMyCartDoesNotContainTicket(firstTicket);
    }

    @Test
    public void testTotalPrice() {
        testMyCart.add(firstTicket);
        testMyCart.add(secondTicket);
        testMyCart.add(thirdTicket);
        assertEquals(251.5, testMyCart.totalPrice());
    }

    // helper
    private void checkMyCartDoesNotContainTicket(Ticket ticket) {
        assertFalse(testMyCart.contains(ticket));
    }

    // helper
    private void checkMyCartContainsTicket(Ticket ticket) {
        assertEquals(1, testMyCart.quantity());
        assertTrue(testMyCart.contains(ticket));
    }
}

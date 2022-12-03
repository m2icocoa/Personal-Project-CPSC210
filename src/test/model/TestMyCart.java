package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestMyCart {
    private MyCart testMyCart;
    private Ticket ticketOne;
    private Ticket ticketTwo;
    private Ticket ticketThree;
    private Ticket ticketFour;
    private Ticket ticketFive;

    @BeforeEach
    public void runBefore() {
        testMyCart = new MyCart("Mii's cart");
        ticketOne =
                new Ticket("lower", 100, 1, 1, 120.0);
        ticketTwo =
                new Ticket("lower", 100, 1, 2,110.0);
        ticketThree =
                new Ticket("lower", 100, 2, 1, 90.5);
        ticketFour =
                new Ticket("lower", 102, 1,1, 120.0);
        ticketFive =
                new Ticket("upper", 201,1,1,120.0);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testMyCart.quantity());
    }

    @Test
    public void testAddOneTicket() {
        testMyCart.addTicket(ticketOne);
        assertEquals(1, testMyCart.quantity());
    }


    @Test
    public void testAddSameTicketMultipleTimes() {
        testMyCart.addTicket(ticketOne);
        testMyCart.addTicket(ticketOne);
        assertEquals(1, testMyCart.quantity());
    }

    @Test
    public void testAddDifferentTickets() {
        testMyCart.addTicket(ticketOne);
        testMyCart.addTicket(ticketTwo);
        assertEquals(2, testMyCart.quantity());
    }

    @Test
    public void testRemoveOneTicket() {
        testMyCart.addTicket(ticketOne);
        testMyCart.removeTicket(ticketOne);
        assertEquals(0,testMyCart.quantity());
        assertFalse(testMyCart.ifContains(ticketOne));
    }

    @Test
    public void testRemoveDifferentTickets() {
        testMyCart.addTicket(ticketOne);
        testMyCart.addTicket(ticketTwo);
        testMyCart.addTicket(ticketThree);
        testMyCart.removeTicket(ticketOne);
        testMyCart.removeTicket(ticketTwo);
        assertEquals(1,testMyCart.quantity());
        assertFalse(testMyCart.ifContains(ticketOne));
        assertFalse(testMyCart.ifContains(ticketTwo));
        assertTrue(testMyCart.ifContains(ticketThree));
    }

    @Test
    public void testRemoveOneTicketButNotThere() {
        testMyCart.addTicket(ticketOne);
        assertFalse(testMyCart.ifContains(ticketTwo));
        testMyCart.removeTicket(ticketTwo);
        assertEquals(1, testMyCart.quantity());
    }

    @Test
    public void testGetTicket() {
        testMyCart.addTicket(ticketOne);
        assertEquals(ticketOne, testMyCart.seeInside(1));
    }

    @Test
    public void MyCartDoesNotContainTicket() {
        testMyCart.addTicket(ticketOne);
        assertFalse(testMyCart.ifContains(ticketTwo));
        assertFalse(testMyCart.ifContains(ticketThree));
        assertFalse(testMyCart.ifContains(ticketFour));
        assertFalse(testMyCart.ifContains(ticketFive));
    }

    @Test
    public void MyCartContainsTicket() {
        testMyCart.addTicket(ticketOne);
        testMyCart.addTicket(ticketTwo);
        testMyCart.addTicket(ticketThree);
        assertTrue(testMyCart.ifContains(ticketOne));
        assertTrue(testMyCart.ifContains(ticketTwo));
        assertTrue(testMyCart.ifContains(ticketThree));
    }

    @Test
    public void testQuantity() {
        testMyCart.addTicket(ticketOne);
        testMyCart.addTicket(ticketTwo);
        testMyCart.addTicket(ticketThree);
        assertEquals(3, testMyCart.quantity());
    }

    @Test
    public void testLevel() {
        testMyCart.addTicket(ticketOne);
        assertEquals("lower", testMyCart.level(0));
    }

    @Test
    public void testSection() {
        testMyCart.addTicket(ticketOne);
        assertEquals(100, testMyCart.section(0));
    }

    @Test
    public void testRow() {
        testMyCart.addTicket(ticketOne);
        assertEquals(1, testMyCart.row(0));
    }

    @Test
    public void testNumber() {
        testMyCart.addTicket(ticketOne);
        assertEquals(1, testMyCart.number(0));
    }

    @Test
    public void testPrice() {
        testMyCart.addTicket(ticketOne);
        testMyCart.addTicket(ticketTwo);
        assertEquals(120.0, testMyCart.price(1));
    }

    @Test
    public void testTotalPriceOneTicket() {
        testMyCart.addTicket(ticketOne);
        testMyCart.addTicket(ticketTwo);
        testMyCart.addTicket(ticketThree);
        assertEquals(110.0, testMyCart.price(2));
    }

    @Test
    public void testTotalPriceMultipleTickets() {
        testMyCart.addTicket(ticketOne);
        testMyCart.addTicket(ticketTwo);
        testMyCart.addTicket(ticketThree);
        assertEquals(320.5, testMyCart.totalPrice());
    }

    @Test
    public void testGetName() {
        assertEquals("Mii's cart", testMyCart.getName());
    }

    @Test
    public void testTotalPriceEmptyCart() {
        assertEquals(0, testMyCart.quantity());
        assertEquals(0.0, testMyCart.totalPrice());
    }
}

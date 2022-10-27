package model;

import java.util.ArrayList;

// Represents my cart with tickets selected
public class MyCart {
    ArrayList<Ticket> cart;

    // EFFECTS: constructs an empty cart
    public MyCart() {
        this.cart = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a ticket into my cart unless it's already there, in which case do nothing
    public void addTicket(Ticket ticket) {
        if (!ifContains(ticket)) {
            cart.add(ticket);
        }
    }

    // MODIFIES: this
    // EFFECTS: if the ticket is in my cart, then remove it from my cart.
    //          Otherwise, do nothing.
    public void removeTicket(Ticket ticket) {
        if (ifContains(ticket)) {
            cart.remove(ticket);
        }
    }

    // EFFECTS: if the exact same ticket is in the cart, return true,
    //          otherwise, return false.
    public boolean ifContains(Ticket selectedTicket) {
        for (Ticket ticket : cart) {
            if (selectedTicket.getLevel().equals(ticket.getLevel())) {
                if (selectedTicket.getSection() == ticket.getSection()) {
                    if (selectedTicket.getRow() == ticket.getRow()) {
                        if (selectedTicket.getNumber() == ticket.getNumber()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // EFFECTS: return the selected ticket in my cart
    public Ticket seeInside(int num) {
        return cart.get(num - 1);
    }

    // EFFECTS: returns the number of tickets in my cart
    public int quantity() {
        return cart.size();
    }

    // EFFECTS: return the level of the selected ticket in my cart
    public String level(int num) {
        return cart.get(num).getLevel();
    }

    // EFFECTS: return the section of the selected ticket in my cart
    public int section(int num) {
        return cart.get(num).getSection();
    }

    // EFFECTS: return the row of the selected ticket in my cart
    public int row(int num) {
        return cart.get(num).getRow();
    }

    // EFFECTS: return the number of the selected ticket in my cart
    public int number(int num) {
        return cart.get(num).getNumber();
    }

    // EFFECTS: return the price of the selected ticket in my cart
    public double price(int num) {
        return cart.get(num).getPrice();
    }

    // EFFECTS: if the cart contains at least one ticket, returns the total price of tickets in my cart,
    //          otherwise, returns $0.0
    public double totalPrice() {
        double priceSum = 0.0;
        if (!(cart.size() == 0)) {
            for (Ticket ticket : cart) {
                priceSum += ticket.getPrice();
            }
            return priceSum;
        }
        return priceSum;
    }
}


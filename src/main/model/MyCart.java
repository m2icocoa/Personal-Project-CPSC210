package model;


import java.util.ArrayList;

public class MyCart {
    ArrayList<Ticket> tickets;

    // EFFECTS: constructs an empty cart
    public MyCart() {
        this.tickets = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a ticket into my cart unless it's already there, in which case do nothing
    public void add(Ticket ticket) {
        if (!tickets.contains(ticket)) {
            tickets.add(ticket);
        }
    }

    // MODIFIES: this
    // EFFECTS: if the ticket is in my cart, then remove it from my cart.
    //          Otherwise, do nothing.
    public void remove(Ticket ticket) {
        if (tickets.contains(ticket)) {
            tickets.remove(ticket);
        }
    }

    // EFFECTS: if the ticket is in my cart, return true.
    //          Otherwise, return false.
    public boolean contains(Ticket ticket) {
        return tickets.contains(ticket);
    }

    // EFFECTS: returns the number of tickets in my cart
    public int quantity() {
        return tickets.size();
    }

    // EFFECTS: returns the selected ticket in my cart
    public Ticket get(int i) {
        return tickets.get(i);
    }

    // EFFECTS: return the level of the selected ticket in my cart
    public String level(int num) {
        return tickets.get(num).getLevel();
    }

    // EFFECTS: return the section of the selected ticket in my cart
    public int section(int num) {
        return tickets.get(num).getSection();
    }

    // EFFECTS: return the row of the selected ticket in my cart
    public int row(int num) {
        return tickets.get(num).getRow();
    }

    // EFFECTS: return the number of the selected ticket in my cart
    public int number(int num) {
        return tickets.get(num).getNumber();
    }

    // EFFECTS: return the price of the selected ticket in my cart
    public double price(int num) {
        return tickets.get(num).getPrice();
    }

    // EFFECTS: return the total price of tickets in my cart
    public double totalPrice() {
        double priceSum = 0.0;
        if (!(tickets.size() == 0)) {
            for (int i = 0; i <= tickets.size() - 1; i++) {
                priceSum += tickets.get(i).getPrice();
            }
            return priceSum;
        }
        return priceSum;
    }
}


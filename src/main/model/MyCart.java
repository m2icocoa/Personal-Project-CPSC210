package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents my cart with tickets selected
public class MyCart implements Writable {
    private final String name;
    ArrayList<Ticket> cart;
    private int indexOfCorresponding;


    // EFFECTS: constructs an empty cart
    public MyCart(String name) {
        this.name = name;
        this.cart = new ArrayList<>();
    }


    // MODIFIES: this
    // EFFECTS: adds a ticket into my cart unless it's already there, in which case do nothing
    public void addTicket(Ticket ticket) {
        if (!ifContains(ticket)) {
            cart.add(ticket);
            EventLog.getInstance().logEvent(new Event("Ticket added to cart."));
        }
    }


    // EFFECTS: returns an unmodifiable list of tickets in this cart
    public List<Ticket> getTickets() {
        return Collections.unmodifiableList(cart);
    }


    // MODIFIES: this
    // EFFECTS: if the ticket is in my cart, then remove it from my cart,
    //          otherwise, do nothing
    public void removeTicket(Ticket ticket) {
        if (ifContains(ticket)) {
            cart.remove(cart.get(indexOfCorresponding));
            EventLog.getInstance().logEvent(new Event("Ticket removed from cart."));
        }
    }


    // MODIFIES: this
    // EFFECTS: removes the ticket at the selected index from my cart
    public void removeTicketForSelectionApp(int i) {
        cart.remove(cart.get(i));
        EventLog.getInstance().logEvent(new Event("Ticket removed from cart."));
    }


    // MODIFIES: this
    // EFFECTS: updates the ticket at the selected index from my cart by preferred ticket typed in the text box
    public void updateTicketForSelectionApp(int i, Ticket ticket) {
        cart.set(i, ticket);
        EventLog.getInstance().logEvent(new Event("Ticket updated."));
    }


    // EFFECTS: if the exact same ticket is in my cart, return true,
    //          otherwise, return false
    public boolean ifContains(Ticket selectedTicket) {
        for (Ticket ticket : cart) {
            if (selectedTicket.getLevel().equals(ticket.getLevel())) {
                if (selectedTicket.getSection() == ticket.getSection()) {
                    if (selectedTicket.getRow() == ticket.getRow()) {
                        if (selectedTicket.getNumber() == ticket.getNumber()) {
                            indexOfCorresponding = cart.indexOf(ticket);
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
        return cart.get(num - 1).getPrice();
    }

    // EFFECTS: return the name of my cart
    public String getName() {
        return name;
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("tickets", myCartToJason());
        return json;
    }

    // Method above were taken from WorkRoom in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git


    // EFFECTS: returns tickets in this cart as a JSON array
    private JSONArray myCartToJason() {
        JSONArray jsonArray = new JSONArray();

        for (Ticket c : cart) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

    // Method above were taken from WorkRoom in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
}


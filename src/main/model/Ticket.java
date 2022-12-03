package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a ticket with seat level, section, row, number and price (in dollars)
public class Ticket implements Writable {
    private String level;       // seat level
    private int section;        // seat section
    private int row;            // seat row in the section
    private int number;         // seat number in the row (from left to right)
    private double price;       // seat price

    // REQUIRES: seatLevel has a non-zero length
    //           seatSection is a positive integer [100,110] or [200,210]
    //           seatRow is a positive integer [1,23]
    //           seatNumber is a positive integer [1,20]
    //           and seatPrice a positive double
    // EFFECTS: seatLevel is set to level
    //          seatSection is set to section
    //          seatRow is set to row
    //          seatNumber is set to number
    //          and seatPrice is set to price
    public Ticket(String seatLevel, int seatSection, int seatRow, int seatNumber, double seatPrice) {
        level = seatLevel;
        section = seatSection;
        row = seatRow;
        number = seatNumber;
        price = seatPrice;
    }

    // EFFECTS: assigns seatLevel to level
    public void assignLevel(String seatLevel) {
        level = seatLevel;
    }

    // EFFECTS: assigns seatSection to section
    public void assignSection(int seatSection) {
        section = seatSection;
    }

    // EFFECTS: assigns seatRow to row
    public void assignRow(int seatRow) {
        row = seatRow;
    }

    // EFFECTS: assigns it to number
    public void assignNumber(int seatNumber) {
        number = seatNumber;
    }

    // REQUIRES: seatRow is [1, 23]
    // EFFECTS: return the price of seats depending on its level and the row number
    public double assignPrice(String seatLevel, int seatRow) {
        if (seatLevel.equals("lower")) {
            price = 50.0 + 2.5 * (23.0 + 1.0 - seatRow);
        } else if (seatLevel.equals("upper")) {
            price = 50.0 + 1.5 * (23.0 + 1.0 - seatRow);
        } else {
            price = 0.0;
        }
        return price;
    }

    public String getLevel() {
        return level;
    }

    public int getSection() {
        return section;
    }

    public int getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    public double getPrice() {
        return price;
    }


    // EFFECTS: returns string representation of this ticket
    public String toString() {
        return "Level: " + level + ", "
                + "Section: " + section + ", "
                + "Row: " + row + ", "
                + "Number: " + number + ", "
                + "Price: $" + price;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("level", level);
        json.put("section", section);
        json.put("row", row);
        json.put("number", number);
        json.put("price", price);
        return json;
    }

    // Method above were taken from Thingy in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
}






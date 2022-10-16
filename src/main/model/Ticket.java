package model;

// Represents a ticket with seat leve, section, row, number and price (in dollars)
public class Ticket {
    private final String level;       // seat level
    private final int section;        // seat section
    private final int row;            // row in the section
    private final int number;         // seat number in the row (from right to left)
    private final double price;          // ticket price


    // constructor
    // REQUIRES: level has a non-zero length
    //           section, row, number and price are zero
    // EFFECTS: selectedLevel is set to level
    //          selectedSection is a positive integer and set to section
    //          selectedRow is a positive integer [1,23] and set to row
    //          selectedNumber is a positive integer [1,20] and set to number
    //          seatPrice is a positive double and set to price
    public Ticket(String selectedLevel, int selectedSection, int selectedRow, int selectedNumber, double seatPrice) {
        level = selectedLevel;
        section = selectedSection;
        row = selectedRow;
        number = selectedNumber;
        price = seatPrice;
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
}






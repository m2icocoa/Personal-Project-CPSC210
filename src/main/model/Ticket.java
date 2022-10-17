package model;

// Represents a ticket with seat leve, section, row, number and price (in dollars)
public class Ticket {
    private String level;       // seat level
    private int section;        // seat section
    private int row;            // row in the section
    private int number;         // seat number in the row (from right to left)
    private double price;       // ticket price


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

    public void assignLevel(String selectedLevel) {
        level = selectedLevel;
    }

    public void assignSection(int selectedSection) {
        section = selectedSection;
    }

    public void assignRow(int selectedRow) {
        row = selectedRow;
    }

    public void assignNumber(int selectedSeatNum) {
        number = selectedSeatNum;
    }

    public double assignPrice(String selectedLevel, int selectedRow) {
        if (selectedLevel.equals("lower")) {
            price = 100.0 + 2.0 * (23.0 + 1.0 - selectedRow);
        } else if (selectedLevel.equals("upper")) {
            price = 80.0 + 1.5 * (23.0 + 1.0 - selectedRow);
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
}






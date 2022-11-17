package ui.gui;

public class SeatInfo {
    private String firstName; // First name
    private int seatNum;

    // Method to initialize Seat fields
    public void reserveSeat(String inFirstName, int inSeatNum) {
        firstName = inFirstName;
        seatNum = inSeatNum;
    }

    // Method to empty a Seat
    public void makeEmpty() {
        firstName = "empty";
    }

    // Method to check if Seat is empty
    public boolean isEmpty() {
        return firstName.equals("empty");
    }

    // Method to print Seat fields
    public void printSeatInfo() {
        System.out.print(firstName + " ");
    }

    public String getFirstName() {
        return firstName;
    }

    public int getSeatNum() {
        return seatNum;
    }
}
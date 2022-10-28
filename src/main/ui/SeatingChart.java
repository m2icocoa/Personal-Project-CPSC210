package ui;

import model.MyCart;
import model.Ticket;

import java.util.Scanner;

// Seating Chart Application
public class SeatingChart {
    private MyCart myCart;
    private Scanner input;
    private Ticket selected =
            new Ticket(null, 0, 0, 0, 0.0);

    // EFFECTS: runs the seating chart
    public SeatingChart() {
        runSeatingChart();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runSeatingChart() {

        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nSee you next time!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "s":
                doSelect();
                break;
            case "n":
                checkHowManyTickets();
                break;
            case "p":
                checkTotalPrice();
                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }
    }

    // conducts a seat selection
    private void doSelect() {
        selectLevel();
        selectSection();
        selectRow();
        selectNum();
        printPrice();
        displayAddOrDoNothing();
        addOrDoNothing();
    }

    // EFFECTS: conducts add or remove
    private void addOrDoNothing() {
        String command = input.next();
        command = command.toLowerCase();

        if (command.equals("y")) {
            addSeatToMyCart();
        } else if (command.equals("n")) {
            runSeatingChart();
        } else {
            System.out.println("Invalid selection.");
        }
    }

    // MODIFIES: this
    // EFFECTS: if the preferred seat level is either lower or upper, conducts a seat level selection,
    //          otherwise says the input is invalid
    private void selectLevel() {
        System.out.println("Enter the seat level you prefer: lower or upper.");
        String selectedLevel = input.next();
        if (selectedLevel.equals("lower") || selectedLevel.equals("upper")) {
            selected.assignLevel(selectedLevel);
        } else {
            System.out.println("You entered an invalid level.");
            selectLevel();
        }
    }

    // MODIFIES: this
    // EFFECTS: if seatSection is either [100, 110] or [200, 210], conducts a seat section selection
    //          otherwise says the input is invalid
    private void selectSection() {
        if (selected.getLevel().equals("lower")) {
            System.out.println(
                    "Enter the seat section you prefer: 100, 101, 102, 103, 104, 105, 106, 107, 108, 109 110.");
            int selectedSection = input.nextInt();
            if (selectedSection >= 100 && selectedSection <= 110) {
                selected.assignSection(selectedSection);
            } else {
                System.out.println("You entered an invalid section.");
                selectSection();
            }
        } else {
            System.out.println(
                    "Enter the seat section you prefer: 200, 201, 202, 203, 204, 205, 206, 207, 208, 209 210.");
            int selectedSection = input.nextInt();
            if (selectedSection >= 200 && selectedSection <= 210) {
                selected.assignSection(selectedSection);
            } else {
                System.out.println("You entered an invalid section.");
                selectSection();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: if seatRow is [1, 23], assigns it to row, conducts a seat row selection
    //          otherwise says the input is invalid
    private void selectRow() {
        System.out.println("Enter the seat row you prefer from 1 to 23.");
        int selectedRow = input.nextInt();
        if (selectedRow >= 1 && selectedRow <= 23) {
            selected.assignRow(selectedRow);
        } else {
            System.out.println("You entered an invalid row.");
        }
    }

    // MODIFIES: this
    // EFFECTS: if seatNumber is is [1, 20], conducts a seat number selection,
    //          otherwise says the input is invalid
    private void selectNum() {
        System.out.println("Enter the seat number you prefer from 1 to 20.");
        int selectedNumber = input.nextInt();
        if (selectedNumber >= 1 && selectedNumber <= 20) {
            selected.assignNumber(selectedNumber);
        } else {
            System.out.println("You entered an invalid number.");
        }
    }

    // EFFECTS: prints price of the preferred seat to the screen
    private void printPrice() {
        System.out.println("The price of selected seat is "
                + selected.assignPrice(selected.getLevel(), selected.getRow())
                + " Dollars");
    }

    // EFFECTS: add the preferred seat to the cart
    private void addSeatToMyCart() {
        if (!myCart.ifContains(selected)) {
            myCart.addTicket(selected);
            System.out.println(
                    "\nLevel: " + selected.getLevel()
                            + "\nSection: " + selected.getSection() + "\nRow: " + selected.getRow()
                            + "\nNumber: " + selected.getNumber() + "\nPrice: " + selected.getPrice()
                            + "\nThis ticket is added to your cart.");
        } else {
            System.out.println("You already have this ticket in your cart.");
        }
        selected = new Ticket(null, 0, 0, 0,0.0);
    }

    // EFFECTS: count how many tickets in the cart
    private void checkHowManyTickets() {
        if (myCart.quantity() == 0 || myCart.quantity() == 1) {
            System.out.println("You have " + myCart.quantity() + " ticket in your cart.");
        } else {
            System.out.println("You have " + myCart.quantity() + " tickets in your cart.");
        }
    }

    // EFFECTS: calculate the total price of tickets in the cart
    private void checkTotalPrice() {
        System.out.println("Total price: $" + myCart.totalPrice());
    }

    // MODIFIES: this
    // EFFECTS: initializes my cart
    private void init() {
        myCart = new MyCart();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("\ts -> select a ticket");
        System.out.println("\tn -> check the number of tickets in your cart");
        System.out.println("\tp -> check the total price of tickets in your cart");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: displays a question where a user wants to add or remove the selected ticket from the cart
    private void displayAddOrDoNothing() {
        System.out.println("\nWould you like to add this ticket to your cart?");
        System.out.println("\ty -> yes");
        System.out.println("\tn -> no");
    }
}
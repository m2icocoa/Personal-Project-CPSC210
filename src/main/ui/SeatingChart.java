package ui;

import model.MyCart;
import model.Ticket;

import java.util.Scanner;

// Seating Chart Application
public class SeatingChart {
    private MyCart myCart;
    private Scanner input;
    private Ticket desired =
            new Ticket(null, 0, 0, 0, 0.0);


    // EFFECTS: runs the seating chart
    public SeatingChart() {
        runSeatingChart();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runSeatingChart() {

        boolean keepGoing = true;
        String command = null;

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

        if (command.equals("s")) {
            doSelect();
        } else if (command.equals("n")) {
            checkHowManyTickets();
        } else {
            System.out.println("Invalid selection.");
        }
    }

    //
    private void doSelect() {
        selectLevel();
        selectSection();
        selectRow();
        selectNum();
        printPrice();
        displayAddOrRemove();
        addOrRemove();
    }


    // EFFECTS: conducts add or remove
    private void addOrRemove() {
        String command = input.next();
        command = command.toLowerCase();

        if (command.equals("a")) {
            addSeatToMyCart();
        } else if (command.equals("r")) {
            removeSeatFromMyCart();
        } else {
            System.out.println("Invalid selection.");
        }
    }


    // MODIFIES: this
    // EFFECTS: conducts a seat level selection
    private void selectLevel() {
        System.out.print("Enter your desired seat level: ");
        String desiredLevel = input.next();
        desired.assignLevel(desiredLevel);
    }

    // MODIFIES: this
    // EFFECTS: conducts a seat section selection
    private void selectSection() {
        System.out.print("Enter your desired seat section: ");
        int desiredSection = input.nextInt();
        desired.assignSection(desiredSection);
    }

    // MODIFIES: this
    // EFFECTS: conducts a seat row selection
    private void selectRow() {
        System.out.print("Enter your desired seat row: ");
        int desiredRow = input.nextInt();
        desired.assignRow(desiredRow);
    }

    // MODIFIES: this
    // EFFECTS: conducts a seat number selection
    private void selectNum() {
        System.out.print("Enter your desired seat number: ");
        int desiredNum = input.nextInt();
        desired.assignNumber(desiredNum);
    }

    // EFFECTS: prints price of the desired seat to the screen
    private void printPrice() {
        System.out.printf("\tPrice: " + desired.assignPrice(desired.getLevel(), desired.getRow()) + " Dollars");
    }

    // EFFECTS: add the desired seat to the cart
    private void addSeatToMyCart() {
        myCart.addTicket(desired);
        System.out.println(
                "\nLevel: " + desired.getLevel() + "\nSection: " + desired.getSection() + "\nRow: " + desired.getRow()
                        + "\nNumber: " + desired.getNumber() + "\nPrice: " + desired.getPrice()
                        + "\nThis ticket is added to your cart.");
    }

    // EFFECTS: remove the desired seat from the cart
    private void removeSeatFromMyCart() {
        myCart.remove(desired);
        System.out.println(
                "\nLevel: " + desired.getLevel() + "\nSection: " + desired.getSection() + "\nRow: " + desired.getRow()
                        + "\nNumber: " + desired.getNumber() + "\nPrice: " + desired.getPrice()
                        + "\nThis ticket is removed from your cart.");
    }

    // EFFECTS: count how many tickets in the cart
    private void checkHowManyTickets() {
        System.out.println("You have " + myCart.quantity() + " tickets in your cart.");
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
        System.out.println("\nMain Option:");
        System.out.println("\ts -> seat selection");
        System.out.println("\tn -> check the number of tickets in your cart");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: displays a question where a user wants to add or remove the selected ticket from the cart
    private void displayAddOrRemove() {
        System.out.println("\n\nWould you like to add or remove this ticket?");
        System.out.println("\ta -> add");
        System.out.println("\tr -> remove");
    }
}

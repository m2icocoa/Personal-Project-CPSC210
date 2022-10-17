package ui;

import model.MyCart;
import model.Ticket;

import java.util.Scanner;

public class SeatingChart {
    private MyCart myCart;
    private Scanner input;
    private final Ticket desired =
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
                printPrice();
            }
        }
        System.out.println("\nSee you next time!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("s")) {
            selectLevel();
            selectSection();
            selectRow();
            selectNum();

        } else {
            System.out.println("Selection not valid...");
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

    private void printPrice() {
        System.out.printf("Price: " + desired.assignPrice(desired.getLevel(), desired.getRow()) + " Dollars");
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
        System.out.println("Option:");
        System.out.println("\ts -> seat selection");
        System.out.println("\tq -> quit");
    }
}

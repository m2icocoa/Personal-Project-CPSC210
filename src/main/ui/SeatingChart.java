package ui;

import model.MyCart;
import model.Ticket;

import java.awt.*;
import java.util.Scanner;

public class SeatingChart {
    private MyCart myCart;
    private Scanner input;

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


            if (command.equals("s")) {
                selectLevel();
                selectSection();
                selectRow();
                selectSeatNum();

                // printSelection(selectedLevel, selectedSection, selectedRow, selectedSeatNum);
            } else if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nSee you next time!");
    }

    public void printSelection(String selectedLevel, int selectedSection, int selectedRow, int selectedSeatNum) {
        System.out.printf("Ticket Selected:\n" + selectedLevel, selectedSection, selectedRow, selectedSeatNum);
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("s")) {
            selectLevel();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void selectLevel() {
        Ticket desired = new Ticket(null, 0, 0, 0, 0.0);
        System.out.print("Enter your desired seat level: ");
        String selectedLevel = input.next();
        desired.assignLevel(selectedLevel);
    }

    // MODIFIES: this
    // EFFECTS: conducts a seat section selection
    private void selectSection() {
        Ticket desired = new Ticket(null, 0, 0, 0, 0.0);
        System.out.print("Enter your desired seat section: ");
        int selectedSection = input.nextInt();
        desired.assignSection(selectedSection);
    }

    // MODIFIES: this
    // EFFECTS: conducts a seat row selection
    private void selectRow() {
        Ticket desired = new Ticket(null, 0, 0, 0, 0.0);
        System.out.print("Enter your desired seat row: ");
        int selectedRow = input.nextInt();
        desired.assignRow(selectedRow);
    }

    // MODIFIES: this
    // EFFECTS: conducts a seat seat number selection
    private void selectSeatNum() {
        Ticket desired = new Ticket(null, 0, 0, 0, 0.0);
        System.out.print("Enter your desired seat number: ");
        int selectedSeatNum = input.nextInt();
        desired.assignNumber(selectedSeatNum);
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

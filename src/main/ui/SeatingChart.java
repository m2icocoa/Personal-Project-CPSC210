package ui;

import model.MyCart;
import model.Ticket;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Seating Chart Application
public class SeatingChart {
    private static final String JSON_STORE = "./data/my-cart.json";
    private MyCart myCart;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Scanner input;
    private Ticket selected =
            new Ticket(null, 0, 0, 0, 0.0);


    // EFFECTS: constructs seating chart and runs application
    public SeatingChart() throws FileNotFoundException {
        input = new Scanner(System.in);
        myCart = new MyCart("Mii's cart");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runSeatingChart();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runSeatingChart() {

        boolean keepGoing = true;
        String command;

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
        if (command.equals("c")) {
            doSelect();
        } else if (command.equals("n")) {
            checkHowManyTickets();
        } else if (command.equals("t")) {
            checkTotalPrice();
        } else if (command.equals("p")) {
            printMyCart();
        } else if (command.equals("s")) {
            saveMyCart();
        } else if (command.equals("l")) {
            loadMyCart();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: conducts a seat selection
    private void doSelect() {
        selectLevel();
        selectSection();
        selectRow();
        selectNum();
        printPrice();
        displayAddOrRemove();
        addOrDoNothing();
    }

    // EFFECTS: conducts addition of a ticket
    private void addOrDoNothing() {
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
    // EFFECTS: if seatSection is either [100, 110] or [200, 210], conducts a seat section selection,
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
    // EFFECTS: if seatRow is [1, 23], assigns it to row, conducts a seat row selection,
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

    // EFFECTS: adds the preferred seat to the cart
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

    // EFFECTS: removes the preferred seat to the cart
    private void removeSeatFromMyCart() {
        if (myCart.ifContains(selected)) {
            myCart.removeTicket(selected);
            System.out.println(
                    "\nLevel: " + selected.getLevel()
                            + "\nSection: " + selected.getSection() + "\nRow: " + selected.getRow()
                            + "\nNumber: " + selected.getNumber() + "\nPrice: " + selected.getPrice()
                            + "\nThis ticket is removed from your cart.");
        } else {
            System.out.println("We could not find the ticket in your cart.");
        }
        selected = new Ticket(null, 0, 0, 0,0.0);
    }

    // EFFECTS: counts how many tickets in the cart
    private void checkHowManyTickets() {
        if (myCart.quantity() == 0 || myCart.quantity() == 1) {
            System.out.println("You have " + myCart.quantity() + " ticket in your cart.");
        } else {
            System.out.println("You have " + myCart.quantity() + " tickets in your cart.");
        }
    }

    // EFFECTS: calculates the total price of tickets in the cart
    private void checkTotalPrice() {
        System.out.println("Total price: $" + myCart.totalPrice());
    }

//    // MODIFIES: this
//    // EFFECTS: initializes my cart
//    private void init() {
//        myCart = new MyCart();
//        input = new Scanner(System.in);
//        input.useDelimiter("\n");
//    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("\tc-> choose a ticket");
        System.out.println("\tn -> check the number of tickets in your cart");
        System.out.println("\tt -> check the total price of tickets in your cart");
        System.out.println("\tp -> print tickets");
        System.out.println("\ts -> save my cart to file");
        System.out.println("\tl -> load my cart from file");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: displays a question where a user wants to add/remove the selected ticket to/from the cart
    private void displayAddOrRemove() {
        System.out.println("\nWould you like to add or remove this ticket?");
        System.out.println("\ta -> add");
        System.out.println("\tr -> remove");
    }

    // EFFECTS: prints all the tickets in my cart to the console
    private void printMyCart() {
        List<Ticket> tickets = myCart.getTickets();

        for (Ticket mc : tickets) {
            System.out.println(mc);
        }
    }

    // EFFECTS: saves the cart to file
    private void saveMyCart() {
        try {
            jsonWriter.open();
            jsonWriter.write(myCart);
            jsonWriter.close();
            System.out.println("Saved " + myCart.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the cart from file
    private void loadMyCart() {
        try {
            myCart = jsonReader.read();
            System.out.println("Loaded " + myCart.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
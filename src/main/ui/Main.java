package ui;

import java.io.FileNotFoundException;

// Represents a seating chart application
public class Main {

    // EFFECTS: runs a seating chart application
    public static void main(String[] args) {
        try {
            new SeatingChart();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
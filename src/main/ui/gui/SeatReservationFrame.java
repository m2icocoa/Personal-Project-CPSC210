package ui.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
public class SeatReservationFrame extends JFrame implements ActionListener {
    private JTextField firstNameField;             // Holds first name
    private JFormattedTextField seatNumField;      // Holds seat number
    private JLabel tableLabel;                     // Label for table display
    private JLabel seatNumLabel;                   // Label for seat number
    private JLabel firstNameLabel;                 // Label for first name
    private JButton reserveButton;                 // Triggers seat reservation
    private JButton quitButton;                    // Triggers termination of GUI
    private JTable seatStatusTable;                // Table tracks seat
    // reservations
    private final static int NUM_SEATS = 5;        // Number of seat in reservation system
    private static ArrayList<SeatInfo> seatResArr; // ArrayList of Seat objects

    int seatNum;           // Seat number
    SeatInfo seatElement;  // Seat information
    String firstName;      // First name

    /* Constructor creates GUI components and adds GUI components
       using a GridBagLayout. */
    SeatReservationFrame() {

        Object[][] tableVals = new Object[5][2];                // Seat reservation table
        String[] columnHeadings = {"Seat Number", "First Name", // Column headings for reservation table
        };
        GridBagConstraints layoutConst = null;                  // GUI component layout

        // Set frame's title
        setTitle("Seat reservation");

        // Add 5 seat objects to ArrayList
        seatResArr = new ArrayList<SeatInfo>();
        seatsAddElements(seatResArr, NUM_SEATS);

        // Make all seats empty
        seatsMakeEmpty(seatResArr);

        // Create seat reservation table
        tableLabel = new JLabel("Seat reservation status:");
        seatNumLabel = new JLabel("Seat Number:");
        firstNameLabel = new JLabel("First Name:");

        seatNumField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        seatNumField.setEditable(true);
        //seatNumField.setValue(0);

        firstNameField = new JTextField(20);
        firstNameField.setEditable(true);
        //firstNameField.setText("John");

        reserveButton = new JButton("Reserve");
        reserveButton.addActionListener(this);

        quitButton = new JButton("Quit");
        quitButton.addActionListener(this);

        // Initialize table
        seatStatusTable = new JTable(tableVals, columnHeadings);
        seatStatusTable.setEnabled(false); // Prevent user input via table

        // Add components using GridBagLayout
        setLayout(new GridBagLayout());

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 1, 0);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        add(tableLabel, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(1, 10, 0, 0);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        layoutConst.gridwidth = 4;
        add(seatStatusTable.getTableHeader(), layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(0, 10, 10, 0);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        layoutConst.gridwidth = 4;
        add(seatStatusTable, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 1, 0);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 0;
        layoutConst.gridy = 3;
        add(seatNumLabel, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(1, 10, 10, 0);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 0;
        layoutConst.gridy = 4;
        add(seatNumField, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 1, 0);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 1;
        layoutConst.gridy = 3;
        add(firstNameLabel, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(1, 10, 10, 0);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 1;
        layoutConst.gridy = 4;
        add(firstNameField, layoutConst);


        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(0, 10, 10, 5);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 4;
        layoutConst.gridy = 4;
        add(reserveButton, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(0, 5, 10, 10);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 5;
        layoutConst.gridy = 4;
        add(quitButton, layoutConst);
    }

    /* Called when either button is pressed. */
    @Override
    public void actionPerformed(ActionEvent event) {

        // Get source of event (2 buttons in GUI)
        JButton sourceEvent = (JButton) event.getSource();

        // User pressed the reserve button
        if (sourceEvent == reserveButton) {
            //seatNum = 1;
            firstName = firstNameField.getText();

            seatElement = new SeatInfo();         // Create new Seat object
            seatElement.reserveSeat(firstName, seatNum);
            seatResArr.set(seatNum, seatElement); // Add seat to ArrayList
            updateTable();                        // Synchronize table with sts ArrayList

            //seatNum++;  //!!!!! this code does not work. how can I change the array number?

            // Show success dialog
            JOptionPane.showMessageDialog(this, "test");

        } else if (sourceEvent == quitButton) {
            dispose();                               // Terminate program
        }
    }

    /* Updates the reservation information displayed by the table */
    public void updateTable() {
        final int seatNumCol = 0;   // Col num for seat numbers
        final int firstNameCol = 1; // Col num for first names
        final int lastNameCol = 2;  // Col num for last names
        final int paidCol = 3;      // Col num for amount paid
        int i;                      // Loop index

        for (i = 0; i < NUM_SEATS && i < seatResArr.size(); ++i) {
            if (seatResArr.get(i).isEmpty()) { // Clear table entries
                seatStatusTable.setValueAt(null, i, seatNumCol);
                seatStatusTable.setValueAt(null, i, firstNameCol);
            } else {                             // Update table with content in the seatResArr ArrayList
                seatStatusTable.setValueAt(seatResArr.get(i).getSeatNum(), i, seatNumCol);
                seatStatusTable.setValueAt(seatResArr.get(i).getFirstName(), i, firstNameCol);
            }
        }
    }

    /* Makes seats empty */
    public static void seatsMakeEmpty(ArrayList<SeatInfo> seatsRes) {
        int i;      // Loop index

        for (i = 0; i < seatsRes.size(); ++i) {
            seatsRes.get(i).makeEmpty();
        }
    }

    /* Adds empty seats to ArrayList */
    public static void seatsAddElements(ArrayList<SeatInfo> seatsRes, int numSeats) {
        int i;     // Loop index

        for (i = 0; i < numSeats; ++i) {
            seatsRes.add(new SeatInfo());
        }
    }

    /* Creates a SeatReservationFrame and makes it visible */
    public static void main(String[] args) {
        // Creates SeatReservationFrame and its components
        SeatReservationFrame myFrame = new SeatReservationFrame();

        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        myFrame.setVisible(true);
    }
}

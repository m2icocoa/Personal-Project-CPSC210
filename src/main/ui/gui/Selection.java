package ui.gui;

import model.Event;
import model.EventLog;
import model.MyCart;
import model.Ticket;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;

// Represents an application's main window
public class Selection implements ActionListener, PropertyChangeListener {

    private static final String JSON_STORE = "./data/my-cart.json";
    private MyCart myCart = new MyCart("Mii's cart");
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    private JFrame frame = new JFrame("Ticket Selection");
    private JTable table = new JTable();
    private DefaultTableModel model = new DefaultTableModel();

    private Object[] tableRow = new Object[5];

    private JLabel levelLabel = new JLabel("Level: ");
    private JLabel sectionLabel = new JLabel("Section: ");
    private JLabel rowLabel = new JLabel("Row: ");
    private JLabel numberLabel = new JLabel("Number: ");
    private JLabel priceLabel = new JLabel("Price: ");

    private NumberFormat sectionFormat = NumberFormat.getNumberInstance();
    private NumberFormat rowFormat = NumberFormat.getNumberInstance();
    private NumberFormat numberFormat = NumberFormat.getNumberInstance();
    private NumberFormat priceFormat = NumberFormat.getCurrencyInstance();

    private JFormattedTextField levelText = new JFormattedTextField();
    private JFormattedTextField sectionText = new JFormattedTextField(sectionFormat);
    private JFormattedTextField rowText = new JFormattedTextField(rowFormat);
    private JFormattedTextField numberText = new JFormattedTextField(numberFormat);
    private JFormattedTextField priceText = new JFormattedTextField(priceFormat);

    private JButton loadButton = new JButton("Load");
    private JButton addButton = new JButton("Add");
    private JButton quitButton = new JButton("Quit");
    private JButton saveButton = new JButton("Save");
    private JButton updateButton = new JButton("Update");
    private JButton deleteButton = new JButton("Delete");

    private String level = "";
    private int section;
    private int row;
    private int number;
    private double price;

    private JLabel explanationZero = new JLabel();
    private JLabel explanationOne = new JLabel();
    private JLabel explanationTwo = new JLabel();
    private JLabel explanationThree = new JLabel();
    private JLabel explanationFour = new JLabel();
    private JLabel explanationFive = new JLabel();
    private JLabel explanationSix = new JLabel();


    // EFFECTS: schedules a job for the event dispatch thread and runs the application
    public static void main(String[] args) {
        new Selection();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
            }
        });
    }


    // EFFECTS: creates the application by putting all the features in one frame
    public Selection() {
        ticketInputSettings();
        allButtonSettings();

        chartSetting();
        explanation();
        createTable();

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(1500, 1000);
        frame.setLayout(null);
        frame.setVisible(true);

        closingAppByClickingX();
    }


    // EFFECTS: creates the ticket selection settings
    private void ticketInputSettings() {
        levelSetting();
        sectionSetting();
        rowSetting();
        numberSetting();
        priceSetting();

        levelLabel.setLabelFor(levelText);
        sectionLabel.setLabelFor(sectionText);
        rowLabel.setLabelFor(rowText);
        numberLabel.setLabelFor(numberText);
        priceLabel.setLabelFor(priceText);
    }


    // EFFECTS: creates the button settings
    private void allButtonSettings() {
        loadButtonSetting();
        saveButtonSetting();
        quitButtonSetting();
        addButtonSetting();
        updateButtonSetting();
        deleteButtonSetting();
    }


    // EFFECTS: when the x button on the right corner is clicked, check if the user wants to save tickets first
    private void closingAppByClickingX() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EventLog eventLog = EventLog.getInstance();
                for (Event event : eventLog) {
                    System.out.print(event);
                }
                quit();
            }
        });
    }


    // EFFECTS: adds the seating chart image in the frame
    private void chartSetting() {
        ImageIcon seatingChart = new ImageIcon(getClass().getResource("chart.png"));
        JLabel chart = new JLabel();
        chart.setIcon(seatingChart);
        chart.setBounds(600, 300, 800, 400);
        frame.add(chart);
    }


    // EFFECTS: puts every line of explanations together and puts in the frame
    private void explanation() {
        explanationSetText();
        explanationZero.setBounds(600, 50, 800, 50);
        explanationOne.setBounds(600, 70, 800, 100);
        explanationTwo.setBounds(600, 90, 800, 100);
        explanationThree.setBounds(600, 110, 800, 100);
        explanationFour.setBounds(600, 130, 800, 100);
        explanationFive.setBounds(600, 150, 800, 100);
        explanationSix.setBounds(600, 170, 800, 100);
        frame.add(explanationZero);
        frame.add(explanationOne);
        frame.add(explanationTwo);
        frame.add(explanationThree);
        frame.add(explanationFour);
        frame.add(explanationFive);
        frame.add(explanationSix);
    }


    // EFFECTS: creates explanations
    private void explanationSetText() {
        explanationZero.setText("How to Use");
        explanationZero.setFont(new Font(null, Font.BOLD, 20));
        explanationOne.setText("1) Enter the seat level you prefer: lower or upper.");
        explanationTwo.setText(
                "2) If you enter 'lower' --> "
                        + "Enter the seat section you prefer: 100, 101, 102, 103, 104, 105, 106, 107, 108, 109 110.");
        explanationThree.setText(
                "\t\t\t\tIf you enter 'upper' --> "
                        + "Enter the seat section you prefer: 200, 201, 202, 203, 204, 205, 206, 207, 208, 209 210.");
        explanationFour.setText("3) Enter the seat row you prefer from 1 to 23.");
        explanationFive.setText("4) Enter the seat number you prefer from 1 to 20.");
        explanationSix.setText("The system will automatically calculate the price of your preferred ticket!");
    }


    // EFFECTS: adds a deletion feature as a button in the frame
    private void deleteButtonSetting() {
        deleteButton.setBounds(320, 150, 100, 40);
        deleteButton.addActionListener(this);
        deleteButton.setActionCommand("Delete");
        frame.add(deleteButton);
    }


    // EFFECTS: adds an updating feature as a button in the frame
    private void updateButtonSetting() {
        updateButton.setBounds(320, 110, 100, 40);
        updateButton.addActionListener(this);
        updateButton.setActionCommand("Update");
        frame.add(updateButton);
    }


    // EFFECTS: adds an addition feature as a button in the frame
    private void addButtonSetting() {
        addButton.setBounds(320, 70, 100, 40);
        addButton.addActionListener(this);
        addButton.setActionCommand("Add");
        frame.add(addButton);
    }


    // EFFECTS: adds a quitting feature as a button in the frame
    private void quitButtonSetting() {
        quitButton.setBounds(230, 10, 100, 25);
        quitButton.addActionListener(this);
        quitButton.setActionCommand("Quit");
        frame.add(quitButton);
    }


    // EFFECTS: adds a saving feature as a button in the frame
    private void saveButtonSetting() {
        saveButton.setBounds(140, 10, 100, 25);
        saveButton.addActionListener(this);
        saveButton.setActionCommand("Save");
        frame.add(saveButton);
    }


    // EFFECTS: adds a loading feature as a button in the frame
    private void loadButtonSetting() {
        loadButton.setBounds(50, 10, 100, 25);
        loadButton.addActionListener(this);
        loadButton.setActionCommand("Load");
        frame.add(loadButton);
    }


    // EFFECTS: adds a text box for a seat level
    private void levelSetting() {
        levelLabel.setBounds(50, 70, 500, 25);
        frame.add(levelLabel);
        levelText.setBounds(140, 70, 165, 25);
        levelText.addPropertyChangeListener("value", this);
        levelText.setValue(level);
        frame.add(levelText);
    }


    // EFFECTS: adds a text box for a seat section
    private void sectionSetting() {
        sectionLabel.setBounds(50, 100, 80, 25);
        frame.add(sectionLabel);
        sectionText.setBounds(140, 100, 165, 25);
        sectionText.addPropertyChangeListener("value", this);
        sectionText.setValue(section);
        frame.add(sectionText);
    }


    // EFFECTS: adds a text box for a seat row
    private void rowSetting() {
        rowLabel.setBounds(50, 130, 80, 25);
        frame.add(rowLabel);
        rowText.setBounds(140, 130, 165, 25);
        rowText.addPropertyChangeListener("value", this);
        rowText.setValue(row);
        frame.add(rowText);
    }


    // EFFECTS: adds a text box for a seat number
    private void numberSetting() {
        numberLabel.setBounds(50, 160, 80, 25);
        frame.add(numberLabel);
        numberText.setBounds(140, 160, 165, 25);
        numberText.addPropertyChangeListener("value", this);
        numberText.setValue(number);
        frame.add(numberText);
    }


    // EFFECTS: adds a text box for a seat price
    private void priceSetting() {
        price = computePrice(level, row);
        priceLabel.setBounds(50, 190, 80, 25);
        frame.add(priceLabel);
        priceText.setBounds(140, 190, 165, 25);
        priceText.addPropertyChangeListener("value", this);
        priceText.setValue(price);
        priceText.setEditable(false);
        priceText.setForeground(Color.red);
        frame.add(priceText);
    }


    // EFFECTS: creates a table that corresponds to text fields
    public void createTable() {
        // create a table model and set a Column Identifiers to this model
        Object[] columns = {"Level", "Section", "Row", "Number", "Price"};
        model.setColumnIdentifiers(columns);

        // set the model to the table
        table.setModel(model);
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("", 1, 22);
        table.setFont(font);
        table.setRowHeight(30);

        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(50, 300, 500, 400);

        frame.add(pane);
        getSelectedRowAction();
    }


    // EFFECTS: gets selected row data from table to text fields
    private void getSelectedRowAction() {
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();

                levelText.setText(model.getValueAt(i, 0).toString());
                sectionText.setText(model.getValueAt(i, 1).toString());
                rowText.setText(model.getValueAt(i, 2).toString());
                numberText.setText(model.getValueAt(i, 3).toString());
                priceText.setText(model.getValueAt(i, 4).toString());
            }
        });
    }


    // MODIFIES: this
    // EFFECTS: when the deletion button is clicked, deletes the row after it is selected
    private void deleteAction() {

        // i = the index of the selected row
        int i = table.getSelectedRow();

        if (i >= 0) {
            model.removeRow(i);
            myCart.removeTicketForSelectionApp(i);
        } else {
            System.out.println("Delete Error");
        }
    }


    // MODIFIES: this
    // EFFECTS: when the addition button is clicked, converts information in text fields into the last row of the table
    private void addAction() {
        tableRow[0] = levelText.getText();
        tableRow[1] = sectionText.getText();
        tableRow[2] = rowText.getText();
        tableRow[3] = numberText.getText();
        tableRow[4] = priceText.getText();

        // add row to the model
        model.addRow(tableRow);
    }


    // EFFECTS: processes user selection
    @Override
    public void actionPerformed(ActionEvent e) {
        String level = levelText.getText();
        int section = Integer.parseInt(sectionText.getText());
        int row = Integer.parseInt(rowText.getText());
        int number = Integer.parseInt(numberText.getText());

        if (e.getActionCommand().equals("Add")) {
            addActionMain(level, section, row, number);
        } else if (e.getActionCommand().equals("Update")) {
            updateAction();
        } else if (e.getActionCommand().equals("Delete")) {
            deleteAction();
        } else if (e.getActionCommand().equals("Save")) {
            save();
        } else if (e.getActionCommand().equals("Quit")) {
            EventLog eventLog = EventLog.getInstance();
            for (Event event : eventLog) {
                System.out.print(event);
            }
            quit();
        } else if (e.getActionCommand().equals("Load")) {
            load();
        }
    }


    // EFFECTS: when the addition button is clicked and if the inputs meet the requirement, then process the addition
    //          and makes the window pop up
    private void addActionMain(String level, int section, int row, int number) {
        Ticket ticket = new Ticket(level, section, row, number, (Double) priceText.getValue());
        if (!level.equals("lower") && !level.equals("upper")) {
            invalidLevelWarning();
        } else if (checkLevelMatchesSection(level, section)) {
            invalidSectionWarning();
        } else if (!(row >= 1 && row <= 23)) {
            invalidRowWarning();
        } else if (!(number >= 1 && number <= 20)) {
            invalidNumberWarning();
        } else if (myCart.ifContains(ticket)) {
            duplicatesWarning();
        } else if (!myCart.ifContains(ticket)) {
            addAction();
            jinMeme();
            myCart.addTicket(ticket);
        }
    }

    // EFFECTS: creates a pop-up display that warns the invalid number error
    private void invalidNumberWarning() {
        JOptionPane.showMessageDialog(null, "You entered an invalid number.",
                "Invalid", JOptionPane.ERROR_MESSAGE);
    }


    // EFFECTS: creates a pop-up display that warns the invalid row error
    private void invalidRowWarning() {
        JOptionPane.showMessageDialog(null, "You entered an invalid row.",
                "Invalid", JOptionPane.ERROR_MESSAGE);
    }


    // EFFECTS: creates a pop-up display that warns the invalid section error
    private void invalidSectionWarning() {
        JOptionPane.showMessageDialog(null, "You entered an invalid section.",
                "Invalid", JOptionPane.ERROR_MESSAGE);
    }


    // EFFECTS: creates a pop-up display that warns the invalid level error
    private void invalidLevelWarning() {
        JOptionPane.showMessageDialog(null, "You entered an invalid level.", "Invalid",
                JOptionPane.ERROR_MESSAGE);
    }


    // EFFECTS: creates a pop-up display that warns the duplication error
    private void duplicatesWarning() {
        JOptionPane.showMessageDialog(null, "You already have this ticket in your cart.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    // EFFECTS: if the ticket is added to the cart successfully, makes the message and image pop up
    private void jinMeme() {
        ImageIcon bts = new ImageIcon(getClass().getResource("jin.png"));
        JLabel icon = new JLabel(bts);
        JLabel text = new JLabel("The ticket is successfully added to your cart. See you at the concert!");
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(icon, BorderLayout.CENTER);
        panel.add(text, BorderLayout.NORTH);
        JOptionPane.showMessageDialog(null, panel, "Successful",
                JOptionPane.PLAIN_MESSAGE);
    }


    // MODIFIES: this
    // EFFECTS: changes the values of each filed by the user inputs
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        Object source = e.getSource();
        if (source == levelText) {
            level = (String) levelText.getValue();
        } else if (source == sectionText) {
            section = ((Number) sectionText.getValue()).intValue();
        } else if (source == rowText) {
            row = ((Number) rowText.getValue()).intValue();
        } else if (source == numberText) {
            number = ((Number) numberText.getValue()).intValue();
        }
        double price = computePrice(level, row);
        priceText.setValue(price);
    }


    // MODIFIES: this
    // EFFECTS: computes the price based on the seat level and row
    double computePrice(String level, int row) {
        double answer;

        if (level.equals("lower")) {
            answer = 50.0 + 2.5 * (23.0 + 1.0 - row);
        } else if (level.equals("upper")) {
            answer = 50.0 + 1.5 * (23.0 + 1.0 - row);
        } else {
            answer = 0.0;
        }
        return answer;
    }


    // EFFECTS: saves the cart to file and makes the message pop up
    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(myCart);
            jsonWriter.close();
            System.out.println("Saved " + myCart.getName() + " to " + JSON_STORE);
            JOptionPane.showMessageDialog(null, "Your tickets are saved.", "Save",
                    JOptionPane.PLAIN_MESSAGE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


    // MODIFIES: this
    // EFFECTS: loads the cart from file and display tickets
    private void load() {
        try {
            myCart = jsonReader.read();
            System.out.println("Loaded " + myCart.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

        List<Ticket> tickets = myCart.getTickets();
        for (Ticket mc : tickets) {

            tableRow[0] = mc.getLevel();
            tableRow[1] = mc.getSection();
            tableRow[2] = mc.getRow();
            tableRow[3] = mc.getNumber();
            tableRow[4] = "$" + mc.getPrice();

            // add row to the model
            model.addRow(tableRow);
        }
    }


    // EFFECTS: saves the tickets and closes the application if user wants to, otherwise just closes the application
    private void quit() {
        int response = JOptionPane.showConfirmDialog(null,
                "Would you like to save your tickets?",
                null,
                JOptionPane.YES_NO_OPTION);
        if (response == 0) {
            save();
            System.exit(0);
        } else if (response == 1) {
            System.exit(0);
        }
    }


    // MODIFIES: this
    // EFFECTS: when the updating button is clicked,
    //          overwrites the information in the table by text fields after the row is selected
    private void updateAction() {
        int i = table.getSelectedRow();
        String level = (String) levelText.getValue();
        int section = ((Number) sectionText.getValue()).intValue();
        int row = ((Number) rowText.getValue()).intValue();
        int number = ((Number) numberText.getValue()).intValue();
        double price = (Double) priceText.getValue();
        Ticket ticket = new Ticket(level, section, row, number, price);

        if (!level.equals("lower") && !level.equals("upper")) {
            invalidLevelWarning();
        } else if (checkLevelMatchesSection(level, section)) {
            invalidSectionWarning();
        } else if (!(row >= 1 && row <= 23)) {
            invalidRowWarning();
        } else if (!(number >= 1 && number <= 20)) {
            invalidNumberWarning();
        } else if (myCart.ifContains(ticket)) {
            duplicatesWarning();
        } else if (i >= 0 && !myCart.ifContains(ticket)) {
            ticketSetValueAt(i, level, section, row, number, price);
            myCart.updateTicketForSelectionApp(i, ticket);
        } else {
            duplicatesWarning();
        }
    }


    // EFFECTS: returns true if level and section input by user does meet the requirement, otherwise returns false
    private boolean checkLevelMatchesSection(String level, int section) {
        return (level.equals("lower") && !(section >= 100 && section <= 110))
                || (level.equals("upper") && !(section >= 200 && section <= 210)) || (section > 110 && section < 200)
                || (section < 100) || (section > 210);
    }


    // EFFECTS: transposes the values of ticket into the selected row
    private void ticketSetValueAt(int i, String level, int section, int row, int number, double price) {
        model.setValueAt(level, i, 0);
        model.setValueAt(section, i, 1);
        model.setValueAt(row, i, 2);
        model.setValueAt(number, i, 3);
        model.setValueAt("$" + price, i, 4);
    }
}

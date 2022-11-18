package ui.gui;

import model.MyCart;
import model.Ticket;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Selection implements ActionListener, PropertyChangeListener {

    // JSON
    private static final String JSON_STORE = "./data/my-cart.json";
    private MyCart myCart = new MyCart("Mii's cart");
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    // Frame
    JFrame frame = new JFrame("Ticket Selection");

    // Table
    JTable table = new JTable();

    // Labels
    private JLabel levelLabel = new JLabel("Level: ");
    private JLabel sectionLabel = new JLabel("Section: ");
    private JLabel rowLabel = new JLabel("Row: ");
    private JLabel numberLabel = new JLabel("Number: ");
    private JLabel priceLabel = new JLabel("Price: ");

    //Formats to format and parse numbers
    private NumberFormat sectionFormat = NumberFormat.getNumberInstance();
    private NumberFormat rowFormat = NumberFormat.getNumberInstance();
    private NumberFormat numberFormat = NumberFormat.getNumberInstance();
    private NumberFormat priceFormat = NumberFormat.getCurrencyInstance();

    //Fields for data entry
    JFormattedTextField levelText = new JFormattedTextField();
    JFormattedTextField sectionText = new JFormattedTextField(sectionFormat);
    JFormattedTextField rowText = new JFormattedTextField(rowFormat);
    JFormattedTextField numberText = new JFormattedTextField(numberFormat);
    JFormattedTextField priceText = new JFormattedTextField(priceFormat);

    //Button
    JButton loadButton = new JButton("Load");
    JButton addButton = new JButton("Add");
    JButton quitButton = new JButton("Quit");
    JButton saveButton = new JButton("Save");
    JButton updateButton = new JButton("Update");
    JButton deleteButton = new JButton("Delete");

    //Values for the fields
    private String level = "";
    private int section;
    private int row;
    private int number;
    private double price;

    // create an array of objects to set the row data
    Object[] tableRow = new Object[5];

    DefaultTableModel model = new DefaultTableModel();

    // Main
    public static void main(String[] args) {
        new Selection();
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                //createAndShowGUI();
            }
        });
    }

    // Everything
    public Selection() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500,1000);
        frame.setLayout(null);

        // Level
        levelLabel.setBounds(10,50,500,25);
        frame.add(levelLabel);
        levelText.setBounds(100,50,165,25);
        levelText.addPropertyChangeListener("value", this);
        levelText.setValue(new String(level));
        frame.add(levelText);

        // Section
        sectionLabel.setBounds(10,80,80,25);
        frame.add(sectionLabel);
        sectionText.setBounds(100,80,165,25);
        sectionText.addPropertyChangeListener("value", this);
        sectionText.setValue(new Integer(section));
        frame.add(sectionText);

        // Row
        rowLabel.setBounds(10,110,80,25);
        frame.add(rowLabel);
        rowText.setBounds(100,110,165,25);
        rowText.addPropertyChangeListener("value", this);
        rowText.setValue(new Integer(row));
        frame.add(rowText);

        // Number
        numberLabel.setBounds(10,140,80,25);
        frame.add(numberLabel);
        numberText.setBounds(100,140,165,25);
        numberText.addPropertyChangeListener("value", this);
        numberText.setValue(new Integer(number));
        frame.add(numberText);

        // Price
        price = computePrice(level, row);
        priceLabel.setBounds(10,170,80,25);
        frame.add(priceLabel);
        priceText.setBounds(100,170,165,25);
        priceText.addPropertyChangeListener("value", this);
        priceText.setValue(new Double(price));
        priceText.setEditable(false);
        priceText.setForeground(Color.red);
        frame.add(priceText);

        //Tell accessibility tools about label/textfield pairs.
        levelLabel.setLabelFor(levelText);
        sectionLabel.setLabelFor(sectionText);
        rowLabel.setLabelFor(rowText);
        numberLabel.setLabelFor(numberText);
        priceLabel.setLabelFor(priceText);

        // Load
        loadButton.setBounds(10,10,100,25);
        loadButton.addActionListener(this);
        loadButton.setActionCommand("Load");
        frame.add(loadButton);

        // Save
        saveButton.setBounds(100,10,100,25);
        saveButton.addActionListener(this);
        saveButton.setActionCommand("Save");
        frame.add(saveButton);

        // Quit
        quitButton.setBounds(190,10,100,25);
        quitButton.addActionListener(this);
        quitButton.setActionCommand("Quit");
        frame.add(quitButton);

        // Add
        addButton.setBounds(300,50,100,40);
        addButton.addActionListener(this);
        addButton.setActionCommand("Add");
        frame.add(addButton);

        // Update
        updateButton.setBounds(300,90,100,40);
        updateButton.addActionListener(this);
        updateButton.setActionCommand("Update");
        frame.add(updateButton);

        // Delete
        deleteButton.setBounds(300,130,100,40);
        deleteButton.addActionListener(this);
        deleteButton.setActionCommand("Delete");
        frame.add(deleteButton);

        // Chart
        ImageIcon seatingChart = new ImageIcon(getClass().getResource("chart.png"));
        JLabel chart = new JLabel();
        chart.setIcon(seatingChart);
        chart.setBounds(600,10,800,500);
        frame.add(chart);

        createTable();

        // set everything visible
        frame.setVisible(true);
    }

    public void createTable() {
        // create a table model and set a Column Identifiers to this model
        Object[] columns = {"Level","Section", "Row", "Number","Price"};
        model.setColumnIdentifiers(columns);

        // set the model to the table
        table.setModel(model);

        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);

        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(10, 300, 500, 400);

        frame.setLayout(null);

        frame.add(pane);

        // button add row
//        addButton.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                tableRow[0] = levelText.getText();
//                tableRow[1] = sectionText.getText();
//                tableRow[2] = rowText.getText();
//                tableRow[3] = numberText.getText();
//                tableRow[4] = priceText.getText();
//
//                // add row to the model
//                model.addRow(tableRow);
//            }
//        });

        // button remove row
        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();
                if (i >= 0) {
                    // remove a row from jtable
                    model.removeRow(i);
                } else {
                    System.out.println("Delete Error");
                }
            }
        });

        // get selected row data From table to textfields
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();

                levelText.setText(model.getValueAt(i, 0).toString());
                sectionText.setText(model.getValueAt(i, 1).toString());
                rowText.setText(model.getValueAt(i, 2).toString());
                numberText.setText(model.getValueAt(i, 3).toString());
                priceText.setText(model.getValueAt(i,4).toString());
            }
        });

        // button update row
        updateButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();

                if (i >= 0) {
                    model.setValueAt(levelText.getText(), i, 0);
                    model.setValueAt(sectionText.getText(), i, 1);
                    model.setValueAt(rowText.getText(), i, 2);
                    model.setValueAt(numberText.getText(), i, 3);
                    model.setValueAt(priceText.getText(),i,4);
                } else {
                    System.out.println("Update Error");
                }
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Load")) {
            load();
        } else if (e.getActionCommand().equals("Add")) {
            String level = levelText.getText();
            int section = Integer.parseInt(sectionText.getText());
            int row = Integer.parseInt(rowText.getText());
            int number = Integer.parseInt(numberText.getText());

            if (!level.equals("lower") && !level.equals("upper")) {
                JOptionPane.showMessageDialog(null, "You entered an invalid level.", "Invalid",
                        JOptionPane.ERROR_MESSAGE);
            } else if ((level.equals("lower") && !(section >= 100 && section <= 110))
                    || (level.equals("upper") && !(section >= 200 && section <= 210))
                    || (section > 110 && section < 200)
                    || (section < 100)
                    || (section > 210)) {
                JOptionPane.showMessageDialog(null, "You entered an invalid section.", "Invalid",
                        JOptionPane.ERROR_MESSAGE);
            } else if (!(row >= 1 && row <= 23)) {
                JOptionPane.showMessageDialog(null, "You entered an invalid row.", "Invalid",
                        JOptionPane.ERROR_MESSAGE);
            } else if (!(number >= 1 && number <= 20)) {
                JOptionPane.showMessageDialog(null, "You entered an invalid number.", "Invalid",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                tableRow[0] = levelText.getText();
                tableRow[1] = sectionText.getText();
                tableRow[2] = rowText.getText();
                tableRow[3] = numberText.getText();
                tableRow[4] = priceText.getText();

                // add row to the model
                model.addRow(tableRow);

                ImageIcon bts = new ImageIcon(getClass().getResource("jin.png"));
                JLabel icon = new JLabel(bts);
                JLabel text = new JLabel("The ticket is successfully added to your cart. See you at the concert!");
                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());
                panel.add(icon, BorderLayout.CENTER);
                panel.add(text, BorderLayout.NORTH);
                JOptionPane.showMessageDialog(null, panel, "Successful",
                        JOptionPane.PLAIN_MESSAGE);

                Ticket ticket = new Ticket(level, section, row, number, (Double) priceText.getValue());
                myCart.addTicket(ticket);
            }
        } else if (e.getActionCommand().equals("Save")) {
            save();
        } else if (e.getActionCommand().equals("Quit")) {
            quit();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        Object source = e.getSource();
        if (source == levelText) {
            level = (String)levelText.getValue();
        } else if (source == sectionText) {
            section = ((Number)sectionText.getValue()).intValue();
        } else if (source == rowText) {
            row = ((Number)rowText.getValue()).intValue();
        } else if (source == numberText) {
            number = ((Number) numberText.getValue()).intValue();
        }
        double price = computePrice(level, row);
        priceText.setValue(new Double(price));
    }


    //Compute the price based on the seat level and row.
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


    // EFFECTS: saves the cart to file
    private void save() {
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
    private void load() {
        try {
            myCart = jsonReader.read();
            System.out.println("Loaded " + myCart.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

        List<Ticket> tickets = myCart.getTickets();

        for (Ticket mc : tickets) {
            System.out.println(mc);
        }
    }

    // EFFECTS: close the frame
    private void quit() {
        frame.dispose();
    }

}



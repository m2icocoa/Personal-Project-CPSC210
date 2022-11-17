package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.text.NumberFormat;

public class Selection implements ActionListener, PropertyChangeListener {

    // Frame
    JFrame frame = new JFrame("Ticket Selection");

    //Values for the fields
    private String level = "";
    private int section = 0;
    private int row = 0;
    private int number = 0;

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
    JButton addButton = new JButton("Add to my cart");
    JButton saveButton = new JButton("Save my cart");

    // Main
    public static void main(String[] args) {
        JOptionPane.showConfirmDialog(null,
                    "Do you want to load your previous data?", null, JOptionPane.YES_NO_OPTION);
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
        double price = computePrice(level, row);
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

        // Add
        addButton.setBounds(10,200,200,25);
        addButton.addActionListener(this);
        addButton.setActionCommand("Add");
        frame.add(addButton);

        // Save
        saveButton.setBounds(10,230,200,25);
        saveButton.addActionListener(this);
        saveButton.setActionCommand("Save");
        frame.add(saveButton);

        // Chart
        ImageIcon seatingChart = new ImageIcon(getClass().getResource("chart.png"));
        JLabel chart = new JLabel();
        chart.setIcon(seatingChart);
        chart.setBounds(400,10,800,500);
        frame.add(chart);

        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add")) {
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
        } else if (e.getActionCommand().equals("Save")) {
            // TODO
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
        double payment = computePrice(level, row);
        priceText.setValue(new Double(payment));
    }


    //Compute the price based on the seat level and row.
    double computePrice(String level, int row) {
        double answer = 0.0;

        if (level.equals("lower")) {
            answer = 50.0 + 2.5 * (23.0 + 1.0 - row);
        } else if (level.equals("upper")) {
            answer = 50.0 + 1.5 * (23.0 + 1.0 - row);
        } else {
            answer = 0.0;
        }
        return answer;
    }
}



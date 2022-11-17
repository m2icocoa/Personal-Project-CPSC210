package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.MessageFormat;
import java.text.NumberFormat;

public class Selection implements ActionListener, PropertyChangeListener {
    //Values for the fields
    private String level = "lower";
    private int section = 100;
    private int row = 1;
    private int number = 1;

    JFrame frame = new JFrame("Ticket Selection");

    JLabel levelLabel = new JLabel("Level: ");
    JLabel sectionLabel = new JLabel("Section: ");
    JLabel rowLabel = new JLabel("Row: ");
    JLabel numberLabel = new JLabel("Number: ");
    JLabel priceLabel = new JLabel("Price: ");

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

    JButton button = new JButton("Add to my cart");

    public Selection() {
        double price = computePrice(level, row);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500,1000);
        frame.setLayout(null);

        levelLabel.setBounds(10,50,500,25);
        frame.add(levelLabel);

        levelText.setBounds(100,50,165,25);
        levelText.addPropertyChangeListener("value", this);
        levelText.setValue(new String(level));
        frame.add(levelText);

        sectionLabel.setBounds(10,80,80,25);
        frame.add(sectionLabel);

        sectionText.setBounds(100,80,165,25);
        sectionText.addPropertyChangeListener("value", this);
        sectionText.setValue(new Integer(section));
        frame.add(sectionText);

        rowLabel.setBounds(10,110,80,25);
        frame.add(rowLabel);

        rowText.setBounds(100,110,165,25);
        rowText.addPropertyChangeListener("value", this);
        rowText.setValue(new Integer(row));
        frame.add(rowText);

        numberLabel.setBounds(10,140,80,25);
        frame.add(numberLabel);

        numberText.setBounds(100,140,165,25);
        numberText.addPropertyChangeListener("value", this);
        numberText.setValue(new Integer(number));
        frame.add(numberText);

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

        button.setBounds(10,200,200,25);
        button.addActionListener(this);
        frame.add(button);

        ImageIcon seatingChart = new ImageIcon(getClass().getResource("chart.png"));
        JLabel chart = new JLabel();
        chart.setIcon(seatingChart);
        chart.setBounds(400,10,800,500);
        frame.add(chart);

        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
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

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
//    private static void createAndShowGUI() {
//        //Create and set up the window.
//        JFrame frame = new JFrame("FormattedTextFieldDemo2");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //Add contents to the window.
//
//        //Display the window.
//        frame.pack();
//        frame.setVisible(true);
//    }

    public static void main(String[] args) {
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

    //Compute the monthly payment based on the loan amount,
    //APR, and length of loan.
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



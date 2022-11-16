package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Selection implements ActionListener {
    JFrame frame = new JFrame("Ticket Selection");
    JLabel levelSelected = new JLabel("Level: ");
    JTextField levelText = new JTextField(20);
    JLabel sectionSelected = new JLabel("Section: ");
    JTextField sectionText = new JTextField();
    JLabel rowSelected = new JLabel("Row: ");
    JTextField rowText = new JTextField();
    JLabel numberSelected = new JLabel("Number: ");
    JTextField numberText = new JTextField();
    JButton button = new JButton("Add to my cart");
    JLabel success = new JLabel("");

    Selection() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,600);
        frame.setLayout(null);

        levelSelected.setBounds(10,50,500,25);
        frame.add(levelSelected);

        levelText.setBounds(100,50,165,25);
        frame.add(levelText);

        sectionSelected.setBounds(10,80,80,25);
        frame.add(sectionSelected);

        sectionText.setBounds(100,80,165,25);
        frame.add(sectionText);

        rowSelected.setBounds(10,110,80,25);
        frame.add(rowSelected);

        rowText.setBounds(100,110,165,25);
        frame.add(rowText);

        numberSelected.setBounds(10,140,80,25);
        frame.add(numberSelected);

        numberText.setBounds(100,140,165,25);
        frame.add(numberText);

        button.setBounds(10,170,200,25);
        button.addActionListener(this);
        frame.add(button);

        ImageIcon seatingChart = new ImageIcon(getClass().getResource("chart.png"));
        JLabel chart = new JLabel();
        chart.setIcon(seatingChart);
        chart.setBounds(400,10,800,500);
        frame.add(chart);

        success.setBounds(10,110,300,25);
        frame.add(success);

        frame.setVisible(true);
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
        }

        if ((level.equals("lower") && !(section >= 100 && section <= 110))
                || (level.equals("upper") && !(section >= 200 && section <= 210))
                || (section > 110 && section < 200)
                || (section < 100)
                || (section > 210)) {
            JOptionPane.showMessageDialog(null, "You entered an invalid section.", "Invalid",
                    JOptionPane.ERROR_MESSAGE);
        }

        if (!(row >= 1 && row <= 23)) {
            JOptionPane.showMessageDialog(null, "You entered an invalid row.", "Invalid",
                    JOptionPane.ERROR_MESSAGE);
        }

        if (!(number >= 1 && number <= 20)) {
            JOptionPane.showMessageDialog(null, "You entered an invalid number.", "Invalid",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            ImageIcon bts = new ImageIcon(getClass().getResource("jin.png"));
            JLabel icon = new JLabel(bts);
            JLabel text = new JLabel("The ticket is successfully added to your cart. See you at the concert!");
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.add(icon,BorderLayout.CENTER);
            panel.add(text,BorderLayout.NORTH);
            JOptionPane.showMessageDialog(null, panel, "Successful",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }
}

package ui.gui;

import javax.swing.*;
import java.awt.*;

public class Selection {
    private static JFrame frame;
    private static JPanel panel;
    private static JLabel levelSelected;
    private static JTextField levelText;
    private static JLabel sectionSelected;
    private static JTextField sectionText;
    private static JLabel rowSelected;
    private static JTextField rowText;
    private static JLabel numberSelected;
    private static JTextField numberText;
    private static JButton button;

    public Selection() {
        levelSelected = new JLabel("Level: ");
        levelSelected.setBounds(10,20,80,25);
        panel.add(levelSelected);
        levelText = new JTextField(20);
        levelText.setBounds(100,20,165,25);
        panel.add(levelText);
        sectionSelected = new JLabel("Section: ");
        sectionSelected.setBounds(10,50,80,25);
        panel.add(sectionSelected);
        sectionText = new JTextField();
        sectionText.setBounds(100,50,165,25);
        panel.add(sectionText);
        rowSelected = new JLabel("Row: ");
        rowSelected.setBounds(10,80,80,25);
        panel.add(rowSelected);
        rowText = new JTextField();
        rowText.setBounds(100,80,165,25);
        panel.add(rowText);
        numberSelected = new JLabel("Number: ");
        numberSelected.setBounds(10,110,80,25);
        panel.add(numberSelected);
        numberText = new JTextField();
        numberText.setBounds(100,110,165,25);
        panel.add(numberText);
        button = new JButton("Add");
        button.setBounds(10,140,80,25);
        panel.add(button);

        ImageIcon seatingChart = new ImageIcon(getClass().getResource("chart.png"));
        // Image scaleImage = seatingChart.getImage().getScaledInstance(seatingChart.getIconWidth(), seatingChart.getIconHeight(),Image.SCALE_SMOOTH);
        // seatingChart = new ImageIcon(scaleImage);
        JLabel chart = new JLabel();
        chart.setIcon(seatingChart);
        chart.setBounds(300,20,800,500);
        panel.add(chart);
    }

    public static void main(String[] args) {
        frame = new JFrame();
        panel = new JPanel();
        panel.setLayout(null);

        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Selection");
        frame.add(panel);

        new Selection();
        frame.setVisible(true);
        panel.setVisible(true);
    }
}

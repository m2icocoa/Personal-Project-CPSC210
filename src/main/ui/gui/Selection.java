package ui.gui;

import javax.swing.*;

public class Selection {
    private static JFrame frame;
    protected static JPanel panel;
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
        frame = new JFrame();
        panel = new JPanel();

        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Selection");
        frame.setVisible(true);
        frame.add(panel);

        panel.setLayout(null);

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

        ImageIcon seatingChart = new ImageIcon("chart.png");
        JLabel chart = new JLabel(seatingChart);
        chart.setBounds(100,20,500,500);
        panel.add(chart);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Selection();
        frame.setVisible(true);
    }
}

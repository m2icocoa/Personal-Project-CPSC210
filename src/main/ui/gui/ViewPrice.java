package ui.gui;

import javax.swing.*;
import java.awt.*;

public class ViewPrice {
    JFrame frame = new JFrame();
    JLabel label = new JLabel("Check total price!");

    ViewPrice() {

        label.setBounds(0,0,500,50);
        label.setFont(new Font(null, Font.PLAIN, 20));

        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,600);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}

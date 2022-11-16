package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LaunchPage implements ActionListener {
    JFrame frame = new JFrame("Main Menu");
    JButton selectionButton = new JButton("Select tickets");
    JButton removalButton = new JButton("Remove tickets");
    JButton viewCart = new JButton("View my cart");
    JButton viewPrice = new JButton("View total price");

    LaunchPage() {
        selectionButton.setBounds(10,10,200,100);
        selectionButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        selectionButton.addActionListener(this);

        removalButton.setBounds(10,110,200,100);
        removalButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        removalButton.addActionListener(this);

        viewCart.setBounds(10,210,200,100);
        viewCart.setAlignmentX(Component.LEFT_ALIGNMENT);
        viewCart.addActionListener(this);

        viewPrice.setBounds(10,310,200,100);
        viewPrice.setAlignmentX(Component.LEFT_ALIGNMENT);
        viewPrice.addActionListener(this);

        frame.add(selectionButton);
        frame.add(removalButton);
        frame.add(viewCart);
        frame.add(viewPrice);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,600);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectionButton) {
            Selection mySelection = new Selection();
        } else if (e.getSource() == removalButton) {
            Removal myRemoval = new Removal();
        } else if (e.getSource() == viewCart) {
            ViewCart myCart = new ViewCart();
        } else {
            ViewPrice myPrice = new ViewPrice();
        }
    }
}

//package ui.gui;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowEvent;
//
//
//public class LaunchPage implements ActionListener {
//    JFrame frame = new JFrame("Main Menu");
//    JButton selectionButton = new JButton("Select tickets");
//    JButton removalButton = new JButton("Remove tickets");
//    JButton viewPrice = new JButton("View total price");
//    JButton quit = new JButton("Quit");
//
//    LaunchPage() {
//        selectionButton.setBounds(10,10,200,100);
//        selectionButton.setAlignmentX(Component.LEFT_ALIGNMENT);
//        selectionButton.addActionListener(this);
//
//        removalButton.setBounds(10,110,200,100);
//        removalButton.setAlignmentX(Component.LEFT_ALIGNMENT);
//        removalButton.addActionListener(this);
//
//        viewPrice.setBounds(10,210,200,100);
//        viewPrice.setAlignmentX(Component.LEFT_ALIGNMENT);
//        viewPrice.addActionListener(this);
//
//        quit.setBounds(10,310,200,100);
//        quit.setAlignmentX(Component.LEFT_ALIGNMENT);
//        quit.addActionListener(this);
//
//        frame.add(selectionButton);
//        frame.add(removalButton);
//        frame.add(viewPrice);
//        frame.add(quit);
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(1500,1000);
//        frame.setLayout(null);
//        frame.setVisible(true);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == selectionButton) {
//            Selection mySelection = new Selection();
//        } else {
//            JOptionPane.showConfirmDialog(null, "Do you want to save your data?", null,
//                    JOptionPane.YES_NO_OPTION);
//            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
//        }
//    }
//}
package ui.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Table {

    public static void main(String[] args){
        JFrame frame = new JFrame();
        JTable table = new JTable();

        // create a table model and set a Column Identifiers to this model 
        Object[] columns = {"Level","Section", "Row", "Number","Price"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        // set the model to the table
        table.setModel(model);

        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);

        // create JTextFields
        JTextField textLevel = new JTextField();
        JTextField textSection = new JTextField();
        JTextField textRow = new JTextField();
        JTextField textNumber = new JTextField();
        JTextField textPrice = new JTextField();

        // create JButtons
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnUpdate = new JButton("Update");

        textLevel.setBounds(20, 220, 100, 25);
        textSection.setBounds(20, 250, 100, 25);
        textRow.setBounds(20, 280, 100, 25);
        textNumber.setBounds(20, 310, 100, 25);
        textPrice.setBounds(20,340,100,25);

        btnAdd.setBounds(150, 220, 100, 25);
        btnUpdate.setBounds(150, 265, 100, 25);
        btnDelete.setBounds(150, 310, 100, 25);

        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);

        frame.setLayout(null);

        frame.add(pane);

        // add JTextFields to the jframe
        frame.add(textLevel);
        frame.add(textSection);
        frame.add(textRow);
        frame.add(textNumber);
        frame.add(textPrice);

        // add JButtons to the jframe
        frame.add(btnAdd);
        frame.add(btnDelete);
        frame.add(btnUpdate);

        // create an array of objects to set the row data
        Object[] row = new Object[5];

        // button add row
        btnAdd.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                row[0] = textLevel.getText();
                row[1] = textSection.getText();
                row[2] = textRow.getText();
                row[3] = textNumber.getText();
                row[4] = textPrice.getText();

                // add row to the model
                model.addRow(row);
            }
        });

        // button remove row
        btnDelete.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();
                if(i >= 0){
                    // remove a row from jtable
                    model.removeRow(i);
                }
                else{
                    System.out.println("Delete Error");
                }
            }
        });

        // get selected row data From table to textfields 
        table.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e){

                // i = the index of the selected row
                int i = table.getSelectedRow();

                textLevel.setText(model.getValueAt(i, 0).toString());
                textSection.setText(model.getValueAt(i, 1).toString());
                textRow.setText(model.getValueAt(i, 2).toString());
                textNumber.setText(model.getValueAt(i, 3).toString());
                textPrice.setText(model.getValueAt(i,4).toString());
            }
        });

        // button update row
        btnUpdate.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();

                if(i >= 0)
                {
                    model.setValueAt(textLevel.getText(), i, 0);
                    model.setValueAt(textSection.getText(), i, 1);
                    model.setValueAt(textRow.getText(), i, 2);
                    model.setValueAt(textNumber.getText(), i, 3);
                    model.setValueAt(textPrice.getText(),i,4);
                }
                else{
                    System.out.println("Update Error");
                }
            }
        });

        frame.setSize(900,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
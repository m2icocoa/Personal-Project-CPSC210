package ui.gui;

import javax.swing.*;

public class Save {

    public Save() {
        JOptionPane.showConfirmDialog(null, "Do you want to save?", "Save", JOptionPane.YES_NO_OPTION);
    }

    public static void main(String[] args) {
        new Save();
    }
}

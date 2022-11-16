package ui.gui;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JOptionPane.showConfirmDialog(null,
                "Do you want to load your previous data?", null, JOptionPane.YES_NO_OPTION);
        LaunchPage launchPage = new LaunchPage();

    }
}

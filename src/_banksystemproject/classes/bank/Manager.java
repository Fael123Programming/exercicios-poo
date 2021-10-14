package _banksystemproject.classes.bank;

import javax.swing.*;

public class Manager {

    public static int menu(String options) {
        return Manager.inputDialogForIntegerNumber(options);
    }

    public static void exit() {
        Manager.showMessage("<<<<< Sessao finalizada >>>>>");
        System.exit(0);
    }

    public static void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public static int inputDialogForIntegerNumber(String msg) {
        return Integer.parseInt(Manager.inputDialog(msg));
    }

    public static double inputDialogForFloatNumber(String msg) {
        return Double.parseDouble(Manager.inputDialog(msg));
    }

    public static String inputDialog(String msg) {
        return JOptionPane.showInputDialog(msg);
    }
}

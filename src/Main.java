package src;
/*
 * This program runs a console that can be used to view and edit infomration in a school system, such as giving grades and marking attendance
 */

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) throws IOException {
        EduMaster eduMaster = new EduMaster();
        eduMaster.startProgram();
    }


}
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

public class Main {
    public static void main(String[] args) throws IOException {
        //EduMaster eduMaster = new EduMaster();
        //eduMaster.startProgram();

        JFrame frame = new JFrame();
        JLabel idLabel = new JLabel("ID: ");
        JTextField idTextField = new JTextField(9);
        JLabel passwwordLabel = new JLabel("Password: ");
        JTextField passwordTextField = new JTextField(9);
        JPanel panel = new JPanel();
        JButton enterButton = new JButton("Enter");

        frame.setLayout(new SpringLayout());
        frame.getContentPane().add(panel);
        panel.add(idLabel);
        panel.add(idTextField);
        panel.add(passwwordLabel);
        panel.add(passwordTextField);
        panel.add(enterButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 100);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }


}
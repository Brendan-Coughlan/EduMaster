package src;
import java.awt.event.*;
import java.beans.EventHandler;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class GUI implements ActionListener{
    private JFrame frame;
    private JLabel idLabel = new JLabel("ID: ");
    private JTextField idTextField = new JTextField(9);
    private JLabel passwwordLabel = new JLabel("Password: ");
    private JTextField passwordTextField = new JTextField(9);
    private JPanel panel;
    private JButton enterButton = new JButton("Enter");

    private boolean enterPressed = false;
        
    GUI() {
        createLoginFrame();
    }

    void createLoginFrame() {
        if(frame != null)
            frame.dispose();
        frame = new JFrame();
        panel = new JPanel();

        frame.setLayout(new SpringLayout());
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 100);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        enterButton.addActionListener(this);
        panel.add(idLabel);
        panel.add(idTextField);
        panel.add(passwwordLabel);
        panel.add(passwordTextField);
        panel.add(enterButton);

        frame.setVisible(true);
    }

    void createActionsFrame() {
        if(frame != null)
            frame.dispose();
        frame = new JFrame();
        panel = new JPanel();

        frame.setLayout(new SpringLayout());
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 100);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        enterButton.addActionListener(this);
        panel.add(enterButton);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        enterPressed = true;
    }

    public void close() {
        frame.dispose();
    }

    public String getIDInput() {
        return idTextField.getText();
    }

    public String getPasswordInput() {
        return passwordTextField.getText();
    }

    public boolean getEnterButtonPressed() {
        return enterPressed;
    }

    public void setEnterButtonPressed(boolean enterPressed) {
        this.enterPressed = enterPressed;
    }
}

package src;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class GUI implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    String roles[] = {"Student", "Teacher", "Admin"};
    JComboBox<String> roleDropdown = new JComboBox<String>(roles);
    private JButton buttons[] = {
            new JButton("Enter"),
            new JButton("Change Psssword"),
            new JButton("Take Attendance"),
            new JButton("Create User"),
            new JButton("Exit"),
    };
    private JTextField textFields[] = {
        new JTextField(9),
        new JTextField(9),
        new JTextField(9),
        new JTextField(9),
        new JTextField(9),
};
    private int action = 0;
    private boolean pressed = false;

    GUI() {
        createLoginFrame();
        for (JButton button : buttons) {
            button.addActionListener(this);
        }
    }
    
    private void createFrame(String title, int x, int y, LayoutManager layout) {
        if (frame != null)
            frame.dispose();
        frame = new JFrame();
        panel = new JPanel();

        frame.setLayout(layout);
        frame.setTitle(title);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(x, y);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    public void createLoginFrame() {
        createFrame("Login", 440, 100, new GridLayout());

        panel.add(new JLabel("ID: "));
        panel.add(textFields[0]);
        panel.add(new JLabel("Password: "));
        panel.add(textFields[1]);
        panel.add(buttons[0]);
        panel.add(buttons[4]);

        frame.setVisible(true);
    }

    public void createActionsFrame() {
        createFrame("Actions", 220, 200, new GridLayout());

        panel.add(buttons[1]);
        panel.add(buttons[2]);
        panel.add(buttons[3]);
        panel.add(buttons[4]);

        frame.setVisible(true);
    }

    public void createChangePasswordFrame() {
        createFrame("Change Password", 475, 100, new GridLayout());

        panel.add(new JLabel("Old Password: "));
        panel.add(textFields[1]);
        panel.add(new JLabel("New Password: "));
        panel.add(textFields[2]);
        panel.add(buttons[0]);
        panel.add(buttons[4]);

        frame.setVisible(true);
    }

    public void createAttendanceFrame(ArrayList<Student> students) {
        createFrame("Attendance", 250, 500, new GridLayout());
        for(Student student : students) {
            panel.add(new JCheckBox(student.getFullName()));
        }
        panel.add(buttons[0]);
        panel.add(buttons[4]);

        frame.setVisible(true);
    }

    public void createNewUserFrame() {
        createFrame("Create User", 100, 300, new GridLayout());
        panel.add(textFields[3]);
        panel.add(textFields[4]);
        panel.add(roleDropdown);
        panel.add(buttons[0]);
        panel.add(buttons[4]);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < buttons.length; i++) {
            if (e.getSource().equals(buttons[i])) {
                action = i;
                break;
            }
        }
        System.out.println("Pressed");
        pressed = true;
    }

    public ArrayList<Boolean> getAttendanceMarks() {
        ArrayList<Boolean> marks = new ArrayList<Boolean>();
        for(Component component : panel.getComponents()) {
            if(component instanceof JCheckBox) {
                marks.add(((JCheckBox)component).isSelected());
            }
        }
        return marks;
    }

    public void close() {
        frame.dispose();
    }

    public Person.Role getSelectedRole() {
        return Person.Role.valueOf(roleDropdown.getSelectedItem().toString());
    }

    public String getTextField(int i) {
        return textFields[i].getText();
    }
    
    public void setTextField(int i, String text) {
        textFields[i].setText(text);
    }

    public boolean getButtonPressed() {
        return pressed;
    }

    public void setButtonPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public int getAction() {
        return action;
    }

    public void showIncorrectLabel(String text) {
        for(Component comp : panel.getComponents()) {
            if(comp instanceof JLabel && ((JLabel)comp).getText().equals(text))
                return;
        }

        JLabel invalidLabel = new JLabel(text);
        invalidLabel.setForeground(Color.red);
        invalidLabel.setVerticalAlignment(JLabel.BOTTOM);
        invalidLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(invalidLabel);
        frame.add(panel);
        frame.setVisible(true);
    }
}

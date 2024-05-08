import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * GUI for EduMaster
 * */
public class GUI implements ActionListener {
    /**
     * The main frame for the GUI
     * */
    private JFrame frame;
    /**
     * The main panel for the GUI
     * */
    private JPanel panel;
    /**
     * The roles that can be chosen
     * */
    String[] roles = {"Student", "Teacher", "Admin"};
    /**
     * The dropdown to select role
     * */
    JComboBox<String> roleDropdown = new JComboBox<String>(roles);
    /**
     * The buttons in the GUI
     * */
    private JButton[] buttons = {
            new JButton("Enter"),
            new JButton("Change Psssword"),
            new JButton("Take Attendance"),
            new JButton("Create User"),
            new JButton("Add Grade"),
            new JButton("Exit"),
            new JButton("View Profile"),
            new JButton("Back"),
    };
    /**
     * The text fields in the GUI
     * */
    private JTextField[] textFields = {
        new JTextField(9),
        new JTextField(9),
        new JTextField(9),
        new JTextField(9),
        new JTextField(9),
        new JTextField(3),
        new JTextField(3),
        new JTextField(6),
};
    /**
     * The action performed
     * */
    private int action = 0;
    /**
     * Has a button been pressed
     * */
    private boolean pressed = false;

    /**
     * The constructor for the GUI
     * */
    GUI() {
        createLoginFrame();
        for (JButton button : buttons) {
            button.addActionListener(this);
        }
    }

    /**
     * Creates a new empty frame with parameters
     * @param title the title of the frame
     * @param x the width of the frame
     * @param y the height of the frame
     * @param layout the layout for the frame
     * */
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

    /**
     * Creates a login frame for EduMaster
     * */
    public void createLoginFrame() {
        createFrame("Login", 440, 100, new GridLayout());

        panel.add(new JLabel("ID: "));
        panel.add(textFields[0]);
        panel.add(new JLabel("Password: "));
        panel.add(textFields[1]);
        panel.add(buttons[0]);
        panel.add(buttons[5]);

        frame.setVisible(true);
    }

    /**
     * Creates actions frame for EduMaster with the options based on the role of the user
     * @param role the role of the user
     * */
    public void createActionsFrame(Person.Role role) {
        createFrame("Actions", 160, 200, new GridLayout());

        switch(role) {
            case Student:
                panel.add(buttons[1]);
                panel.add(buttons[6]);
                panel.add(buttons[5]);
                break;
            case Teacher:
                panel.add(buttons[1]);
                panel.add(buttons[2]);
                panel.add(buttons[4]);
                panel.add(buttons[6]);
                panel.add(buttons[5]);
                break;
            case Admin:
                panel.add(buttons[1]);
                panel.add(buttons[3]);
                panel.add(buttons[4]);
                panel.add(buttons[6]);
                panel.add(buttons[5]);
                break;
        }

        frame.setVisible(true);
    }

    /**
     * Creates a change password frame for EduMaster
     * */
    public void createChangePasswordFrame() {
        createFrame("Change Password", 475, 100, new GridLayout());

        panel.add(new JLabel("Old Password: "));
        panel.add(textFields[1]);
        panel.add(new JLabel("New Password: "));
        panel.add(textFields[2]);
        panel.add(buttons[0]);
        panel.add(buttons[7]);

        frame.setVisible(true);
    }

    /**
     * Creates an attendance frame for the EduMaster
     * @param students the stuudents in the EduMaster
     * */
    public void createAttendanceFrame(ArrayList<Student> students) {
        createFrame("Attendance", 400, 500, new GridLayout());
        for(Student student : students) {
            panel.add(new JCheckBox(student.getFullName()));
        }
        
        panel.add(new JLabel("Date (Optional): "));
        panel.add(textFields[7]);
        panel.add(buttons[0]);
        panel.add(buttons[7]);
        frame.setVisible(true);
    }

    /**
     * Creates a new user frame for EduMaster
     * */
    public void createNewUserFrame() {
        createFrame("Create User", 100, 300, new GridLayout());
        panel.add(new JLabel("First Name: "));
        panel.add(textFields[3]);
        panel.add(new JLabel("Last Name: "));
        panel.add(textFields[4]);
        panel.add(new JLabel("Role: "));
        panel.add(roleDropdown);
        panel.add(buttons[0]);
        panel.add(buttons[7]);

        frame.setVisible(true);
    }

    /**
     * Creates a new grade frame for EduMaster
     * */
    public void createNewGradeFrame() {
        createFrame("New Grade", 400, 200, new GridLayout());
        panel.add(new JLabel("ID: "));
        panel.add(textFields[0]);
        panel.add(new JLabel("Grade: "));
        panel.add(textFields[5]);
        panel.add(new JLabel("Title: "));
        panel.add(textFields[6]);
        panel.add(new JLabel("Date (Optional): "));
        panel.add(textFields[7]);
        panel.add(buttons[0]);
        panel.add(buttons[7]);

        frame.setVisible(true);
    }

    /**
     * Creates a view profile frame for EduMaster
     * @param person the person whose profile is being viewed
     * */
    public void createProfileFrame(Person person) {
        createFrame("Profile", 375, 100, new GridLayout());
        ArrayList<String> list = person.getList();
        panel.add(new JLabel("ID: " + list.get(0)));
        panel.add(new JLabel("Role: " + list.get(1)));
        panel.add(new JLabel("First Name: " + list.get(3)));
        panel.add(new JLabel("Last Name: " + list.get(4)));
        panel.add(new JLabel("GPA: " + list.get(5)));
        panel.add(buttons[7]);

        frame.setVisible(true);
    }

    /**
     * Runs if an Ac
     * */
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < buttons.length; i++) {
            if (e.getSource().equals(buttons[i])) {
                action = i;
                break;
            }
        }
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

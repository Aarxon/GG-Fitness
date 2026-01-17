import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame
{
    public MainWindow()
    {
        openGUI();
    }

    public void openGUI()
    {
        JFrame frame = new JFrame();
        DatabaseConnection db = new DatabaseConnection();

        frame.setTitle("Gym Management System");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());


        JPanel welcomePanel= new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));

        // Welcome
        JLabel welcome = new JLabel("Welcome to the Gym Management System");
        welcome.setFont(new Font("Arial", Font.BOLD, 40));
        welcome.setHorizontalAlignment(SwingConstants.CENTER);


        JButton addBtn = new JButton("Add a new user to the database");
        JButton findBtn = new JButton("Find a user");
        JButton updateBtn = new JButton("Update an exisiting user");
        JButton deleteBtn = new JButton("Delete a user");

        welcomePanel.add(Box.createVerticalStrut(350));
        welcomePanel.add(welcome);
        welcomePanel.add(addBtn);
        welcomePanel.add(findBtn);
        welcomePanel.add(updateBtn);
        welcomePanel.add(deleteBtn);


        frame.add(welcomePanel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void addNewUser()
    {
        JLabel firstName = new JLabel("First Name");
        JLabel lastName = new JLabel("Last Name");
        JLabel email = new JLabel("Email");
        JLabel password = new JLabel("Password");
        JLabel phone = new JLabel("Phone");

    }


}

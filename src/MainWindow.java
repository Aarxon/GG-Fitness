import javax.swing.*;
import java.util.Scanner;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame
{
    DatabaseOperations dbops = new DatabaseOperations();
    Scanner input = new Scanner(System.in);

    public MainWindow()
    {
        loginScreen();
    }

    public void loginScreen()
    {
        int choice = 0;
        System.out.println("Welcome to GG Fitness");

        while(choice == 0)
        {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            choice = input.nextInt();

            switch(choice)
            {

                case 1:

                    dbops.loginUser();
                    break;

                case 2:

                    dbops.createNewUser();
                    break;

                case 3:
                    System.out.println("Exiting... ");
                    System.exit(0);
            }

        }
    }

}

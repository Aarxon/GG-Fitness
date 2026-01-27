import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class DatabaseOperations extends DatabaseConnection
{
    Scanner input = new Scanner(System.in);
    DatabaseConnection dbc = new DatabaseConnection();

    Connection connection = null;
    PreparedStatement pstat = null;
    ResultSet resultSet = null;




    public DatabaseOperations()
    {

    }

    public void createNewUser()
    {
        int i = 0;
        String firstName;
        String lastName;
        String email;
        String password;
        int phone;

        System.out.println("Enter your first name: ");
        firstName = input.next();
        System.out.println("Enter your last name: ");
        lastName = input.next();
        System.out.println("Enter your email: ");
        email = input.next();
        System.out.println("Enter your password: ");
        password = input.next();
        System.out.println("Enter your phone number: ");
        phone = input.nextInt();

        User user = new User(firstName, lastName, email, password, phone);

        try
        {
            connection = dbc.startConnection();
            pstat = connection.prepareStatement("INSERT INTO Users (first_name, last_name, email, password, phone_number) VALUES (?,?,?,?,?) ");

            pstat.setString(1, firstName);
            pstat.setString(2, lastName);
            pstat.setString(3, email);
            pstat.setString(4, password);
            pstat.setInt(5, phone);

            i = pstat.executeUpdate();
            System.out.println(i + " Record created");
            connection = dbc.closeConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void  loginUser()
    {
        String email;
        String password;

        System.out.println("Enter your email: ");
        email = input.next();
        System.out.println("Enter your password: ");
        password = input.next();

        try
        {
            String retrieve = "SELECT * FROM customers WHERE Email = '" + email + "' AND Password = '" + password + "'";

            pstat = connection.prepareStatement(retrieve);
            resultSet = pstat.executeQuery();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }


    }

    public void update()
    {

    }

    public void delete()
    {

    }
}

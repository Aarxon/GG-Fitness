import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.PreparedStatement;

public class DatabaseConnection
{
    Connection connection = null;
    Properties props = new Properties();

    public DatabaseConnection()
    {
        startConnection();
    }

    public void startConnection()
    {
        try
        {
            //Using properties so I don't hardcode in all the connection information
            props.load(new FileInputStream("configProperties"));
            final String DBurl = props.getProperty("db.url");
            final String DBuser = props.getProperty("db.user");
            final String DBpassword = props.getProperty("db.password");

            connection = DriverManager.getConnection(DBurl, DBuser, DBpassword);

            System.out.println("Database connection established");
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection()
    {
        try
        {
            if(connection != null)
            {
                connection.close();
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}


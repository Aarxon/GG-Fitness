import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection
{
    Connection connection = null;
    Properties props = new Properties();

    public DatabaseConnection()
    {

    }

    public Connection startConnection()
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
        return connection;
    }

    public Connection closeConnection()
    {
        try
        {
            if(connection != null)
            {
                connection.close();
                System.out.println("Database connection closed");
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return connection;
    }
}


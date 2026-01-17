import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DatabaseOperations extends DatabaseConnection
{
    PreparedStatement pstat = null;

    public DatabaseOperations()
    {

    }

    public void createNewUser()
    {
        int i = 0;

        try
        {
            pstat = connection.prepareStatement("INSERT INTO Users () ");

            i = pstat.executeUpdate();
            System.out.println(i + " Record created");
        }
        catch (SQLException e) {}
    }

    public void  read()
    {

    }

    public void update()
    {

    }

    public void delete()
    {

    }
}

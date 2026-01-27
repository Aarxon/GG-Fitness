public class Membership
{
    boolean status = false;
    String startDate = "";
    String expiryDate = "";
    String type = "";

    public Membership(boolean status, String startDate, String expiryDate, String type)
    {
        this.status = status;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
        this.type = type;
    }

}

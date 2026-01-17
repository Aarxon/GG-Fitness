public class Membership
{
    boolean active = false;
    String startDate = "";
    String expiryDate = "";

    public Membership(boolean active, String startDate, String expiryDate)
    {
        this.active = active;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
    }

}

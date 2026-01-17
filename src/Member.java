public class Member extends User
{
    Membership membership;

    public Member(String firstName, String lastName, String email, String password, int phone, int userID, Membership membership)
    {
        super(firstName, lastName, email, password, phone, userID);
        this.membership = membership;
    }

}

package com.ggfitness.model;

public class User
{
    private int user_id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Membership membership;


    public User(int user_id, String firstName, String lastName, String email, String password, String phone)
    {
        this.user_id = user_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public User(String firstName, String lastName, String email, String password, String phone)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }


    public int getUser_id()
    {
        return user_id;
    }
    public String getFirstName()
    {
        return firstName;
    }

    public String getlastName()
    {
    return lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public String getPhone()
    {
        return phone;
    }
}

package com.ggfitness.model;

public class Admin extends User
{
    private int admin_id;
    public Admin (int admin_id, String firstName, String lastName, String email, String password, String phone)
    {
        super(firstName, lastName, email, password, phone);
        this.admin_id = admin_id;
    }

}

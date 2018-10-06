package com.example.ace.makemyentry;

/**
 * Created by Ace on 30-Sep-18.
 */


public class Employee {

    private int SSO;
    private String employeeName;
    private String date;
    private int amount;

    public Employee() {
    }

    public Employee(int SSO, String employeeName, String date, int amount) {
        this.SSO = SSO;
        this.employeeName = employeeName;
        this.date = date;
        this.amount = amount;
    }

    public int getSSO() {
        return SSO;
    }

    public void setSSO(int SSO) {
        this.SSO = SSO;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

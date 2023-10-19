package org.example;

public class Employee {
    private int employeeId;
    private String name;
    private double hoursWorked;
    private double payRate;

    public Employee(int id, String n, double hrs, double rate)
    {
        this.employeeId = id;
        this.name = n;
        this.hoursWorked= hrs;
        this.payRate = rate;
    }
    public int getEmployeeId ()
    {
        return this.employeeId;
    }
    public String getName()
    {
        return this.name;

    }
    public double getGrossPay()
    {
        return this.hoursWorked * this.payRate;
    }

    public void setEmployeeId(int id)
    {
        this.employeeId = id;
    }
    public void setName(String n)
    {
        this.name = n;
    }
    public void setHoursWorked(double hours)
    {
        this.hoursWorked = hours;
    }
    public  void setPayRate(double rate)
    {
        this.payRate = rate;
    }
}
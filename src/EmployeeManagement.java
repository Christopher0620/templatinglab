// Name: Christopher Tineo
// Assignment: Homework 3
// This program manages employee payroll information using a menu-driven interface, categorizing employees as Full-Time, Part-Time, or Contractors.

import java.util.*;
import java.io.*;

// Abstract Employee class defining common properties and the calculatePay abstract method
abstract class Employee {
    private int _id; // Employee ID
    private String _name; // Employee Name

    // Default constructor
    public Employee() {
        _id = 0;
        _name = "";
    }

    // Constructor to initialize Employee attributes
    public Employee(int id, String name) {
        _id = id;
        _name = name;
    }

    // Getters for ID and Name
    public int getId() { return _id; }
    public String getName() { return _name; }

    // Setters for ID and Name
    public void setId(int id) { _id = id; }
    public void setName(String name) { _name = name; }

    // Abstract method to calculate pay, must be implemented by concrete classes
    public abstract float calculatePay();

    // Returns a string representation of the employee
    public String toString() {
        return "ID: " + _id + ", Name: " + _name;
    }

    // Override equals method for Employee class
    public boolean equals(Employee e) {
        return _id == e._id && _name.equals(e._name); // Compare by ID and name
    }
}

// FullTimeEmp class with salary specific to full-time employees
class FullTimeEmp extends Employee {
    private float _salary;

    // Constructor for FullTimeEmp
    public FullTimeEmp(int id, String name, float salary) {
        super(id, name);
        _salary = salary;
    }

    // Override equals method for FullTimeEmp
    public boolean equals(Employee e) {
        if (super.equals(e) && e instanceof FullTimeEmp) {
            return _salary == ((FullTimeEmp) e)._salary;
        }
        return false;
    }

    // Override toString method
    public String toString() {
        return super.toString() + ", Salary: " + _salary;
    }

    // Calculate pay for full-time employees
    public float calculatePay() {
        return _salary / 24; // Full-time employees are paid yearly
    }
}

// PartTimeEmp class with hourly rate and hours worked for part-time employees
class PartTimeEmp extends Employee {
    private int _hoursWorked;
    private float _hourlyRate;

    // Constructor for PartTimeEmp
    public PartTimeEmp(int id, String name, int hoursWorked, float hourlyRate) {
        super(id, name);
        _hoursWorked = hoursWorked;
        _hourlyRate = hourlyRate;
    }

    // Override equals method for PartTimeEmp
    public boolean equals(Employee e) {
        if (super.equals(e) && e instanceof PartTimeEmp) {
            return _hoursWorked == ((PartTimeEmp) e)._hoursWorked && _hourlyRate == ((PartTimeEmp) e)._hourlyRate;
        }
        return false;
    }

    // Override toString method
    public String toString() {
        return super.toString() + ", Hours Worked: " + _hoursWorked + ", Hourly Rate: " + _hourlyRate;
    }

    // Calculate pay for part-time employees
    public float calculatePay() {
        return _hoursWorked * _hourlyRate;
    }
}

// Contractor class with projects completed and rate per project for contractors
class Contractor extends Employee {
    private int _projectsCompleted; // Number of projects completed
    private float _ratePerProject; // Rate paid per project

    // Constructor to initialize Contractor attributes
    public Contractor(int id, String name, int projectsCompleted, float ratePerProject) {
        super(id, name); // Call the superclass constructor
        setProjectsCompleted(projectsCompleted); // Set completed projects
        setRatePerProject(ratePerProject); // Set rate per project
    }

    // Getter for projectsCompleted
    public int getProjectsCompleted() {
        return _projectsCompleted;
    }

    // Setter for projectsCompleted
    public void setProjectsCompleted(int projectsCompleted) {
        _projectsCompleted = projectsCompleted; // Update the projects completed
    }

    // Getter for ratePerProject
    public float getRatePerProject() {
        return _ratePerProject;
    }

    // Setter for ratePerProject
    public void setRatePerProject(float ratePerProject) {
        _ratePerProject = ratePerProject; // Update the rate per project
    }

    // Override equals method for Contractor
    public boolean equals(Employee e) {
        if (e instanceof Contractor) {
            return getId() == e.getId() &&
                    getName().equals(e.getName()) &&
                    getProjectsCompleted() == ((Contractor) e).getProjectsCompleted() &&
                    getRatePerProject() == ((Contractor) e).getRatePerProject();
        }
        return false; // Return false if the object is not a Contractor
    }

    // Override toString method to include projects completed and rate per project
    public String toString() {
        return super.toString() + ", Projects Completed: " + getProjectsCompleted() +
                ", Rate per Project: " + getRatePerProject(); // Include all relevant info
    }

    // Calculate pay for contractors
    public float calculatePay() {
        return getProjectsCompleted() * getRatePerProject(); // Total pay based on completed projects
    }
}

// Main program to manage employees and payroll
public class EmployeeManagement {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>(); // Create list to store employees
        loadEmployeesFromFile(employees, "employees.txt"); // Load employees from file
        Scanner scan = new Scanner(System.in); // Create a scanner for user input
        int input = 0; // Initialize input outside the loop

        // Start the menu loop (sentinel loop pattern)
        while (input != 4) {
            // Display the menu
            System.out.println("1. Print Full-Time Payroll");
            System.out.println("2. Print Part-Time Payroll");
            System.out.println("3. Print Contractor Payroll");
            System.out.println("4. Exit (Enter 4 to stop)");

            // Get user input
            System.out.print("Choose an option: ");
            if (scan.hasNextInt()) {
                input = scan.nextInt(); // Read user input

                // Check the user input
                if (input == 1) {
                    // Print full-time payroll
                    printEmployeesByType(employees, "FullTime");
                } else if (input == 2) {
                    // Print part-time payroll
                    printEmployeesByType(employees, "PartTime");
                } else if (input == 3) {
                    // Print contractor payroll
                    printEmployeesByType(employees, "Contractor");
                } // if input == 4, the loop will end automatically
            } else {
                scan.nextLine(); // Clear invalid input and continue the loop
            }
        }
    }

    // Method to load employees from a file
    private static void loadEmployeesFromFile(ArrayList<Employee> employees, String filename) {
        try {
            Scanner infile = new Scanner(new File(filename)); // Open the file for reading
            while (infile.hasNextLine()) { // Loop through each line in the file
                String line = infile.nextLine(); // Read each line
                String[] data = line.split(","); // Split the line into components
                int type = Integer.parseInt(data[0]); // Get employee type
                int id = Integer.parseInt(data[1]); // Get employee ID
                String name = data[2]; // Get employee name

                // Depending on the type, create an employee and add to list
                if (type == 1) {
                    float salary = Float.parseFloat(data[3]);
                    employees.add(new FullTimeEmp(id, name, salary));
                } else if (type == 2) {
                    int hoursWorked = Integer.parseInt(data[3]);
                    float hourlyRate = Float.parseFloat(data[4]);
                    employees.add(new PartTimeEmp(id, name, hoursWorked, hourlyRate));
                } else if (type == 3) {
                    int projectsCompleted = Integer.parseInt(data[3]);
                    float ratePerProject = Float.parseFloat(data[4]);
                    employees.add(new Contractor(id, name, projectsCompleted, ratePerProject));
                }
            }
            infile.close(); // Close the file after reading
        } catch (Exception e) {
            System.out.println("Problem reading in file");
            System.exit(-1); // In case of error, stop the program
        }
    }

    // Method to print employees by type
    private static void printEmployeesByType(ArrayList<Employee> employees, String employeeType) {
        for (Employee e : employees) { // Loop through each employee in the list
            if (employeeType.equals("FullTime") && e instanceof FullTimeEmp) {
                System.out.println(e + ", Pay: " + e.calculatePay());
            } else if (employeeType.equals("PartTime") && e instanceof PartTimeEmp) {
                System.out.println(e + ", Pay: " + e.calculatePay());
            } else if (employeeType.equals("Contractor") && e instanceof Contractor) {
                System.out.println(e + ", Pay: " + e.calculatePay());
            }
        }
    }
}

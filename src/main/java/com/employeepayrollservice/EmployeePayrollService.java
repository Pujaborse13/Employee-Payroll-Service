package com.employeepayrollservice;

import java.util.ArrayList;
import java.util.Scanner;

class EmployeePayroll {
    private int id;
    private String name;
    private double salary;


    public EmployeePayroll(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee ID: " + id + ", Name: " + name + ", Salary: " + salary;
    }
}

public class EmployeePayrollService {
    private ArrayList<EmployeePayroll> employeeList = new ArrayList<>();

    public void readEmployeeData() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Employee Salary: ");
        double salary = scanner.nextDouble();

        EmployeePayroll employee = new EmployeePayroll(id, name, salary);
        employeeList.add(employee);
    }

    public void writeEmployeeData() {
        System.out.println("Employee Payroll Details:");
        for (EmployeePayroll employee : employeeList) {
            System.out.println(employee);
        }
    }

    public static void main(String[] args) {
        EmployeePayrollService payrollService = new EmployeePayrollService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    payrollService.readEmployeeData();
                    break;
                case 2:
                    payrollService.writeEmployeeData();
                    break;
                case 3:
                    System.out.println("Exiting the program.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

package com.employeepayrollservice;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class EmployeePayroll {
    private int id;
    private String name;
    private double salary;

    public EmployeePayroll(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
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
    private static final String PAYROLL_FILE_PATH = "employee_payroll.txt";

    public static void main(String[] args) {

        List<EmployeePayroll> employeeList = readEmployeePayrollFromFile();


        if (!employeeList.isEmpty()) {
            System.out.println("Performing analysis on employee payroll data:");

            double averageSalary = calculateAverageSalary(employeeList);
            System.out.println("Average Salary: " + averageSalary);

            EmployeePayroll highestSalaryEmployee = findHighestSalaryEmployee(employeeList);
            System.out.println("Employee with Highest Salary: " + highestSalaryEmployee);

            EmployeePayroll lowestSalaryEmployee = findLowestSalaryEmployee(employeeList);
            System.out.println("Employee with Lowest Salary: " + lowestSalaryEmployee);
        } else {
            System.out.println("No employee payroll data found.");
        }
    }

    private static List<EmployeePayroll> readEmployeePayrollFromFile() {
        List<EmployeePayroll> employeeList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PAYROLL_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse the employee details from the line
                String[] parts = line.split(", ");
                int id = Integer.parseInt(parts[0].split(": ")[1]);
                String name = parts[1].split(": ")[1];
                double salary = Double.parseDouble(parts[2].split(": ")[1]);

                // Create an EmployeePayroll object and add it to the list
                employeeList.add(new EmployeePayroll(id, name, salary));
            }
        } catch (IOException e) {
            System.out.println("Error reading employee payroll file.");
            e.printStackTrace();
        }
        return employeeList;
    }


    private static double calculateAverageSalary(List<EmployeePayroll> employeeList) {
        double totalSalary = 0;
        for (EmployeePayroll employee : employeeList) {
            totalSalary += employee.getSalary();
        }
        return totalSalary / employeeList.size();
    }

    private static EmployeePayroll findHighestSalaryEmployee(List<EmployeePayroll> employeeList) {
        EmployeePayroll highestSalaryEmployee = employeeList.get(0);
        for (EmployeePayroll employee : employeeList) {
            if (employee.getSalary() > highestSalaryEmployee.getSalary()) {
                highestSalaryEmployee = employee;
            }
        }
        return highestSalaryEmployee;
    }
    private static EmployeePayroll findLowestSalaryEmployee(List<EmployeePayroll> employeeList) {
        EmployeePayroll lowestSalaryEmployee = employeeList.get(0);
        for (EmployeePayroll employee : employeeList) {
            if (employee.getSalary() < lowestSalaryEmployee.getSalary()) {
                lowestSalaryEmployee = employee;
            }
        }
        return lowestSalaryEmployee;
    }
}


























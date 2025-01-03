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


    @Override
    public String toString() {
        return "Employee ID: " + id + ", Name: " + name + ", Salary: " + salary;
    }
}

public class EmployeePayrollService {


    private static final String PAYROLL_FILE_PATH = "employee_payroll.txt";

    public static void main(String[] args) {

        List<EmployeePayroll> employeeList = new ArrayList<>();
        employeeList.add(new EmployeePayroll(1, "Alice", 50000));
        employeeList.add(new EmployeePayroll(2, "Bob", 60000));
        employeeList.add(new EmployeePayroll(3, "Charlie", 70000));

        writeEmployeePayrollToFile(employeeList);

        int entries = countEntriesInFile();
        System.out.println("Number of entries in the file: " + entries);
    }


    private static void writeEmployeePayrollToFile(List<EmployeePayroll> employeeList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PAYROLL_FILE_PATH))) {
            for (EmployeePayroll employee : employeeList) {
                writer.write(employee.toString());
                writer.newLine();
            }
            System.out.println("Employee payroll written to file: " + PAYROLL_FILE_PATH);
        } catch (IOException e) {
            System.out.println("Error writing to file.");
            e.printStackTrace();
        }
    }
    private static int countEntriesInFile() {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(PAYROLL_FILE_PATH))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
            e.printStackTrace();
        }
        return count;
    }

}

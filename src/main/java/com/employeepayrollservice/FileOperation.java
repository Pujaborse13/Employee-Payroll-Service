package com.employeepayrollservice;

import java.io.File;
import java.io.IOException;

public class FileOperation {
    public static void main(String[] args) {

        String directoryPath = "demo file";
        String filePath = directoryPath + "exampleFile.txt";
        String extension = ".txt";

        File file = new File(filePath);
        System.out.println("File Exists: " + file.exists());

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            boolean dirCreated = directory.mkdir();
            System.out.println("Directory Created: " + dirCreated);
        }

        try {
            boolean fileCreated = file.createNewFile();
            System.out.println("File Created: " + fileCreated);
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }


        try {
            boolean fileCreated = file.createNewFile();
            System.out.println("File Created: " + fileCreated);
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }

        System.out.println("\nFiles with extension " + extension + ":");
        File[] filteredFiles = directory.listFiles((dir, name) -> name.endsWith(extension));
        if (filteredFiles != null) {
            for (File f : filteredFiles) {
                System.out.println(f.getName());
            }
        }

        if (file.exists()) {
            boolean deleted = file.delete();
            System.out.println("\nFile Deleted: " + deleted);
            System.out.println("File Exists After Deletion: " + file.exists());
        }



    }
}
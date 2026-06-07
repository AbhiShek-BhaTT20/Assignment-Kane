package com.assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

//Assignment-1
public class VisaFilter {
	public static void main(String[] args) {
        String inputFile = "src/main/resources/input.txt";   
        String outputFile = "output.csv"; 

        List<String[]> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while((line = br.readLine()) != null) {
                if (line.trim().startsWith("Name"))
                	continue;

                String[] parts = line.split(",");
                if (parts.length < 3) 
                	continue;

                String name = parts[0].trim();
                String ageStr = parts[1].trim();
                String address = String.join(",", Arrays.copyOfRange(parts, 2, parts.length - 1)).trim();
                String email = parts[parts.length - 1].trim();
                if (address.isEmpty()) 
                	continue;
                if (!isValidEmail(email)) 
                	continue;

                if (!address.toLowerCase().contains("india"))
                	continue;

                String category;
                try{
                    int age = Integer.parseInt(ageStr);
                    category = (age > 18) ? "Adult" : "Kid";
                }catch (NumberFormatException e) {
                    continue; 
                }

                list.add(new String[]{name, category});
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        try(PrintWriter pw = new PrintWriter(new FileWriter(outputFile))) {
            pw.println("Name, Category");
            for(String[] entry : list) {
                pw.println(entry[0] + "," + entry[1]);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Expected CSV file created as : " + outputFile +" in file root.");
    }
    private static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(regex, email);
    }

}

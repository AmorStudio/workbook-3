package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        try{
            FileReader myFile = new FileReader("employees.csv");
            BufferedReader ReadFile = new BufferedReader(myFile);

            String readLine;
            readLine = ReadFile.readLine();
            while((readLine = ReadFile.readLine()) !=null)
            {
                //readLine = "10|Dana Wyatt|52.5|12.50"
                String [] SeparatedLine = readLine.split("\\|");
                // SeparatedLine = ["10","Dana Wyatt", "52.5","12.50"]
                int id = Integer.parseInt(SeparatedLine[0]);
                String name = SeparatedLine[1];
                double hoursworked = Double.parseDouble(SeparatedLine[2]);
                double rate = Double.parseDouble(SeparatedLine[3]);
                Employee e1 = new Employee(id, name, hoursworked, rate);
                System.out.printf("Employee Id: %d\nEmployee: %s\nPay: %.2f\n", e1.getEmployeeId(), e1.getName(), e1.getGrossPay());
                System.out.println();
            }
            ReadFile.close();

        }
        catch(IOException e){
            System.out.println("ERROR- Invalid File");
        }
    }
}
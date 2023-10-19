package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try{
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter the name of the employee file to process: ");
            String userInputForFile = scanner.nextLine();

            FileReader myFile = new FileReader(userInputForFile +".csv");
            BufferedReader readFile = new BufferedReader(myFile);

            System.out.println("Enter the name of the payment file to create: ");
            String userInputForFileOutput = scanner.nextLine();

            boolean csv = userInputForFileOutput.contains("csv");


            FileWriter writer = new FileWriter(userInputForFileOutput);

            String readLine;
            readLine = readFile.readLine();
            if(csv) {
                writer.write("id|name|gross pay");
            }
            else{
                writer.write("[\n");
            }
            while((readLine = readFile.readLine()) !=null)
            {
                //readLine = "10|Dana Wyatt|52.5|12.50"
                String [] separatedLine = readLine.split("\\|");
                // separatedLine = ["10","Dana Wyatt", "52.5","12.50"]
                int id = Integer.parseInt(separatedLine[0]);
                String name = separatedLine[1];
                double hoursWorked = Double.parseDouble(separatedLine[2]);
                double rate = Double.parseDouble(separatedLine[3]);

                Employee e1 = new Employee(id, name, hoursWorked, rate);
                System.out.printf("Employee Id: %d\nEmployee: %s\nPay: %.2f\n", e1.getEmployeeId(),e1.getName(), e1.getGrossPay());
                System.out.println();
                if(csv){

                    writer.write(e1.getEmployeeId()+"|"+ e1.getName() + "|"+ e1.getGrossPay()+ "\n");

                }
                else{
                    String s = String.format("""
   {"id":" %d","name": "%s", "Gross-Pay": "%.2f"},
   """, e1.getEmployeeId(), e1.getName(), e1.getGrossPay());
                    writer.write(s);
                }
            }
            if(!csv)
            {
                writer.write("]");
            }
            readFile.close();
            writer.close();

        }
        catch(IOException e){
            System.out.println("ERROR- Invalid File");
        }
    }
}
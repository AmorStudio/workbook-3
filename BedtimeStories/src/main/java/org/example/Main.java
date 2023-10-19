package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
// Ask User for story name
        System.out.println("Please enter the name of the story: \n1.Goldilocks \n2.hansel_and_gretel \n3.mary_had_a_little_lamb\n");
        Scanner scanner = new Scanner(System.in);
        String UserInput = scanner.nextLine();

        String FileName = UserInput + ".txt";
        try {

            FileInputStream ReadStory = new FileInputStream(FileName);
            Scanner scanner1 = new Scanner(ReadStory);
            int line = 0;
            String Readline;
            while(scanner1.hasNextLine())
            {
                Readline = scanner1.nextLine();
                line ++;
                System.out.println(line+ ". "+ Readline);
            }
            scanner1.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        // Scan the file with User input name
        //Print each line from file with line number


    }
}
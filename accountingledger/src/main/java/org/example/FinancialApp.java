package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.Scanner;



// this java application is designed to manage financial transactions including deposits and payments
// it allows users to add,view and generate reports for their financial transactions
// Lets break the code down step by step


// this is a record called Financial Entry
// Records are a concise way to create classes with data fields, automatically generating constructors, getters, 'equals' and hashcode methods


record FinancialEntry(String date, String description, double amount, String vendor) {
}









// this is the ledger class
// it represents the ledger for storing financial entries like a bookkeeper
// it maintains a list of FinancialEntry objects
class Ledger {


    private final List<FinancialEntry> entries;

    public Ledger() {

        //  In the Ledger class constructor, entries is initialized as an ArrayList:
        this.entries = new ArrayList<>();
        // This ensures that when a new Ledger is created,
        // it starts with an empty list of financial entries.

        // Throughout the code, you'll see methods like addEntry:
        // when you add a deposit or payment , it is appended to the ArrayList.

    }



// this is my addEntry method
    // this method allows you to add a financial entry to the ledger
    public void addEntry(FinancialEntry entry) {
        entries.add(entry);

    }





 // The getAllEntries , getDepositEntries and getPaymentEntries Methods below will allow me to successfully retrieve the user entries
 // if the user's payment or deposit amount is more than 0
    public List<FinancialEntry> getAllEntries() {
        return entries;
    }

    public List<FinancialEntry> getDepositEntries() {
        List<FinancialEntry> deposits = new ArrayList<>();
        for (FinancialEntry entry : entries) {
            if (entry.amount() > 0) {
                deposits.add(entry);
            }
        }
        return deposits;
    }

    public List<FinancialEntry> getPaymentEntries() {
        List<FinancialEntry> payments = new ArrayList<>();
        for (FinancialEntry entry : entries) {
            if (entry.amount() < 0) {
                payments.add(entry);
            }
        }
        return payments;
    }

// Querying Entries: Methods like getAllEntries, getDepositEntries, getPaymentEntries, and searchByVendor
// use the ArrayList to filter, retrieve, and return specific subsets/elements of financial entries based on user queries.

// this is my SearchByVendor method
    // this method takes a vendor name and returns a list of financial Entries for that Vendor
    public List<FinancialEntry> searchByVendor(String vendorName) {
        List<FinancialEntry> vendorEntries = new ArrayList<>();
        for (FinancialEntry entry : entries) {
            if (entry.vendor().equalsIgnoreCase(vendorName)) {
                vendorEntries.add(entry);
            }
        }
        return vendorEntries;
    }
}
// This is my financial app class
// it contains the 'main method' which serves as the entry point for the financial application
// This is an introduction to the entire app

public class FinancialApp {

    // the main method provides a user-friendly text-based menu for user interaction
    // it creates an instance of the ledger class, initializes a scanner for user input and provides a menu for user interaction
    public static void main(String[] args) throws IOException {
        Ledger ledger = new Ledger();
        Scanner scanner = new Scanner(System.in);
        char choice;




        //
        // it utilizes a 'do-while loop to continuously display the menu until the user chooses to exit
        // the menu offers the following options "D" to add a deposit
        do {
            System.out.println("Home Screen");
            System.out.println("D) Add Deposit");
            // "P" to make a payment
            System.out.println("P) Make Payment (Debit)");
            // "L" to view the ledger
            System.out.println("L) Ledger");
            // "X" to exit the application
            System.out.println("X) Exit");
            System.out.print("Please choose an option: ");

// Here I implemented a scanner to capture User input, and it reads the first character of the input as the Users choice
            choice = scanner.next().charAt(0);

// the switch statement is used to manage different user choices, determining the corresponding actions:
            switch (Character.toUpperCase(choice)) {
                case 'D' -> addDeposit(ledger, scanner);
                // if "D" is chosen, the 'addDeposit' method is invoked to add a deposit
                case 'P' -> makePayment(ledger, scanner);
                // if "P" is chosen, the 'makePayment' method is invoked to record a payment
                case 'L' -> showLedger(ledger, scanner);
                // if "L" is chosen the 'showLedger' method is invoked to view financial entries
                case 'X' -> System.out.println("Exiting the application.");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 'X');
    }


// This is my 'showLedger' method
    // it is responsible for displaying the ledger screen,
// This provides another menu that allows the user to navigate various options related to the financial ledger

    private static void showLedger(Ledger ledger, Scanner scanner) throws IOException {
        char ledgerOption;
// a do-while loop is employed to repeatedly display the
// ledger screen until the user chooses to go back to the home screen by entering '0' or selects 'H' to return home
        do {
            // options in the ledger menu include viewing all entries ('A')
            // or displaying deposit entries ('D') and etc
            System.out.println("Ledger Screen");
            System.out.println("A) All - Display all entries");
            System.out.println("D) Deposits - Display deposit entries");
            System.out.println("P) Payments - Display payment entries");
            System.out.println("R) Reports - Access reports");
            System.out.println("0) Back - Go back to the home page");
            System.out.println("H) Home - Go back to the home page");
            System.out.print("Please choose an option: ");

            ledgerOption = scanner.next().charAt(0);
// Based on the users choice a
// switch statement is used to determine the corresponding Actions:

            switch (Character.toUpperCase(ledgerOption)) {
                case 'A' -> displayEntries(ledger.getAllEntries());
                /* if 'A' is chosen the 'displayEntries' method is invoked to show all ledger entries */
                case 'D' -> displayEntries(ledger.getDepositEntries());
                /* if d is chosen , deposit entries are displayed */
                case 'P' -> displayEntries(ledger.getPaymentEntries());
                /* if 'P' is chosen payment entries are displayed*/
                case 'R' -> showReports(ledger, scanner);
                /* if 'R' is chosen the 'showReports' method is invoked to access financial reports */
                case '0' -> { }
                /* if '0' is entered, the method does nothing and effectively returns to the home screen */
                case 'H' -> {
                    /* if 'H' is selected, the method terminates with a return statement , returning to the main loop in the main method */
                    return;
                }
                default -> System.out.println("Invalid ledger option. Please try again.");
            }
            // The while (ledgerOption != '0') condition means that the loop will continue running as long as the ledgerOption is not equal to '0'.
            // When the user enters '0,' the loop will exit, and the program will return to the home screen.
        } while (ledgerOption != '0');
    }
// This is my 'showReports' method and inside it is responsible for displaying the reports' menu, allowing the user to access financial reports
    // similar to the 'show-ledger' screen
    private static void showReports(Ledger ledger, Scanner scanner) {
        char reportOption;
// it employs a do-while loop to continuously display the reports menu until the user decides to go back to the ledger screen
        // in this case 'O' to return back to the ledger screen or 'O' to return to home
        do {
            // options in the reports menu include generating MonthToDate, PreviousMonth , YearToDate, PreviousYear and searching by Vendor options 1-5
            System.out.println("Reports");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back - Go back to the Ledger screen");
            System.out.println("H) Home - Go back to the home page");
            System.out.print("Please choose a report option: ");
// so it is Ledger --> Reports --> Users Choice
            reportOption = scanner.next().charAt(0);
            // Depending on the selected report option , a Switch Statement is used to determine the corresponding actions
            // options 1-5 call specific methods to generate the corresponding financial reports.
            // if '0' is entered, the method does nothing, effectively returning to the ledger screen menu
            switch (Character.toUpperCase(reportOption)) {
                case '1' -> generateMonthToDateReport(ledger);
                case '2' -> generatePreviousMonthReport(ledger);
                case '3' -> generateYearToDateReport(ledger);
                case '4' -> generatePreviousYearReport(ledger);
                case '5' -> searchByVendor(ledger, scanner);
                case '0' -> { }
                case 'H' -> {
                    return;
                }

               // if the user enters an invalid report option, an error message is displayed and the user is prompted to try again.
                default -> System.out.println("Invalid report option. Please try again.");
            }
            // Keep running as long as it is not prompted to go back or go to the home menu
        } while (reportOption != '0' && reportOption != 'H');
    }

    private static void generatePreviousYearReport(Ledger ledger) {

        Calendar currentDate = Calendar.getInstance();
        int currentYear = currentDate.get(Calendar.YEAR);

        List<FinancialEntry> entries = ledger.getAllEntries();

        double totalDeposits = 0;
        double totalPayments = 0;


        String previousYearStr = Integer.toString(currentYear - 1);

        for (FinancialEntry entry : entries) {
            String entryYear = entry.date().substring(0, 4);
         // the substring represents the year
            if (entryYear.equals(previousYearStr)) {
                if (entry.amount() > 0) {
                    totalDeposits += entry.amount();
                } else {
                    totalPayments += entry.amount();
                }
            }
        }

        System.out.println("Previous Year Report (" + previousYearStr + "):");
        System.out.println("Total Deposits: $" + totalDeposits);
        System.out.println("Total Payments: $" + (-totalPayments));
        // the Negative amount represents payments
    }

    // This method is declared as private static to limit its visibility and make it accessible from within the class.
// It calculates the YearToDate financial report.
    private static void generateYearToDateReport(Ledger ledger) {
        // It starts by creating a Calendar instance named currentDate.
        // A Calendar object provides various methods for manipulating dates and times.
        Calendar currentDate = Calendar.getInstance();

        // Retrieving Entries: It obtains a list of financial entries from the ledger object.
        // This list is stored in the entries variable, which contains all financial transactions.
        List<FinancialEntry> entries = ledger.getAllEntries();

        double totalDeposits = 0;
        double totalPayments = 0;


        String currentYearStr = String.valueOf(currentDate.get(Calendar.YEAR));

        for (FinancialEntry entry : entries) {
            String entryYear = entry.date().substring(0, 4);

            if (entryYear.equals(currentYearStr)) {
                if (entry.amount() > 0) {
                    totalDeposits += entry.amount();
                } else {
                    totalPayments += entry.amount();
                }
            }
        }

        System.out.println("Year-to-Date Report (" + currentYearStr + "):");
        System.out.println("Total Deposits: $" + totalDeposits);
        System.out.println("Total Payments: $" + (-totalPayments));
    }

    private static void generatePreviousMonthReport(Ledger ledger) {

        // this calculates where we are right now, today's date
        Calendar currentDate = Calendar.getInstance();

        currentDate.add(Calendar.MONTH, -1); // Move to the previous month.

        List<FinancialEntry> entries = ledger.getAllEntries();
        double totalDeposits = 0;
        double totalPayments = 0;

        String previousMonthYear = String.valueOf(currentDate.get(Calendar.YEAR));

        for (FinancialEntry entry : entries) {
            String entryMonthYear = entry.date().substring(0, 7);

            if (entryMonthYear.equals(previousMonthYear)) {
                if (entry.amount() > 0) {
                    totalDeposits += entry.amount();
                } else {
                    totalPayments += entry.amount();
                }
            }
        }

        System.out.println("Previous Month Report (" + previousMonthYear + "):");
        System.out.println("Total Deposits: $" + totalDeposits);
        System.out.println("Total Payments: $" + (-totalPayments));
    }

    private static void generateMonthToDateReport(Ledger ledger) {
        Calendar currentDate = Calendar.getInstance();

        List<FinancialEntry> entries = ledger.getAllEntries();
        double totalDeposits = 0;
        double totalPayments = 0;

        String currentMonthYear = String.valueOf(currentDate.get(Calendar.YEAR));

        for (FinancialEntry entry : entries) {
            String entryMonthYear = entry.date().substring(0, 7);

            if (entryMonthYear.equals(currentMonthYear)) {
                if (entry.amount() > 0) {
                    totalDeposits += entry.amount();
                } else {
                    totalPayments += entry.amount();
                }
            }
        }

        System.out.println("Month-to-Date Report (" + currentMonthYear + "):");
        System.out.println("Total Deposits: $" + totalDeposits);
        System.out.println("Total Payments: $" + (-totalPayments));
    }

    private static void addDeposit(Ledger ledger, Scanner scanner) throws IOException {
        String date = getUserInput(scanner, "Enter the deposit date (YYYY-MM-DD): ");
        String description = getUserInput(scanner, "Enter the deposit description: ");
        double amount = Double.parseDouble(getUserInput(scanner, "Enter the deposit amount: "));
        String vendor = getUserInput(scanner, "Enter the vendor name: ");

        FinancialEntry entry = new FinancialEntry(date, description, amount, vendor);
        ledger.addEntry(entry);
        saveToCSV(entry);
        System.out.println("Deposit information saved.");
    }

    private static void makePayment(Ledger ledger, Scanner scanner) throws IOException {
        String date = getUserInput(scanner, "Enter the payment date (YYYY-MM-DD): ");
        String description = getUserInput(scanner, "Enter the payment description: ");
        double amount = -Double.parseDouble(getUserInput(scanner, "Enter the payment amount: "));
        String vendor = getUserInput(scanner, "Enter the vendor name: ");

        FinancialEntry entry = new FinancialEntry(date, description, amount, vendor);
        ledger.addEntry(entry);
        saveToCSV(entry);
        System.out.println("Payment information saved.");
    }

    private static String getUserInput(Scanner scanner, String prompt) {
        // displays the prompt message to the user, instructing them on what to enter.
        System.out.print(prompt);
        // reads the user's input as a string, and this string is returned as the result of the method.
        return scanner.next();
    }

    private static void displayEntries(List<FinancialEntry> entries) {
        if (entries.isEmpty()) {
            System.out.println("No entries found.");
            return;
        }
        // prints the table header
        System.out.println("Date | Description | Amount | Vendor");
        // inside the loop, for each entry, it concatenates the values of entry.date(),
        // entry.description(), entry.amount(), and entry.vendor() to form a single line of text that represents the entry's details.
        for (FinancialEntry entry : entries) {
            System.out.println(entry.date() + " | " + entry.description() + " | $" + entry.amount() + " | " + entry.vendor());
        }
    }

    private static void searchByVendor(Ledger ledger, Scanner scanner) {
        String vendorName = getUserInput(scanner, "Enter vendor name: ");
        // searchByVendor is a method in the Ledger class, now being implemented in this class
        // and it is intended to search for financial entries by a specific vendor's name.
        List<FinancialEntry> vendorEntries = ledger.searchByVendor(vendorName);
        displayEntries(vendorEntries);
    }
    // This is my 'saveToCSV' method
    // this method appends/add financial entries to the "Transactions.csv" file
    // it first takes 'FinancialEntry' object as a parameter
    private static void saveToCSV(FinancialEntry entry) throws IOException {
        // opens a FileWriter in append mode, meaning it will add new entries to existing files
        // Similar to the Arraylist
        try (FileWriter writer = new FileWriter("Transactions.csv", true))
                // Here we tell the 'FileWriter' to open the file in append mode
                // maintaining a running log of all financial entries
        {
            // it formats the entry's date, description, amount and vendor name with the plate symbol
            writer.append(entry.date()).append("|");
            writer.append(entry.description()).append("|");
            writer.append(String.valueOf(entry.amount())).append("|");
            writer.append(entry.vendor()).append("\n");
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }
}
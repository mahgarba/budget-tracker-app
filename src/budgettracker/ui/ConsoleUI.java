package budgettracker.ui;

import budgettracker.model.Category;
import budgettracker.model.Income;
import budgettracker.model.Transaction;
import budgettracker.service.TransactionService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleUI {
    private Scanner scanner;
    private TransactionService service;

    public ConsoleUI(Scanner scanner, TransactionService service){
        this.scanner = scanner;
        this.service = service;
    }

    public void start(){
        while (true){
            displayMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice){
                case 1:
                    addIncome();
                    break;
                case 2:
                    addExpense();
                    break;
                case 3:
                    viewTransactions();
                    break;
                case 4:
                    viewBalance();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu(){
        System.out.println("\n=== Budget Tracker ===");
        System.out.println("1. Add Income");
        System.out.println("2. Add Expense");
        System.out.println("3. View Transactions");
        System.out.println("4. View Balance");
        System.out.println("5. Exit");
    }

    private void addIncome(){
        Transaction transaction = getTransactionInput("Income");
        if (transaction != null){
            service.addIncome(transaction.getDate(), transaction.getDescription(), transaction.getAmount(), transaction.getCategory());
            System.out.println("Income added successfully");
        }
    }

    private void addExpense(){
        Transaction transaction = getTransactionInput("Expense");
        if (transaction != null){
            service.addExpense(transaction.getDate(), transaction.getDescription(), transaction.getAmount(), transaction.getCategory());
            System.out.println("Expense added successfully");
        }
    }

    private Transaction getTransactionInput(String type){
        try{
            System.out.print("Enter date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());

            System.out.print("Enter description: ");
            String description = scanner.nextLine();

            System.out.print("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter category: ");
            String categoryName = scanner.nextLine();

            return new Income(date, description, amount, new Category(categoryName));
        } catch (DateTimeParseException e){
            System.err.println("Invalid date format. Please use YYYY-MM-DD.");
        } catch (NumberFormatException e){
            System.err.println("Invalid amount format. Please enter a number.");
        }

        return null;
    }

    private void viewTransactions(){
        System.out.println("\n=== Transaction History ===");
        for (Transaction t : service.getTransactions()){
            System.out.println(t);
        }
    }

    private void viewBalance(){
        System.out.println("\n=== Financial Summary ===");
        System.out.printf("Total Income: $%.2f\n", service.getTotalIncome());
        System.out.printf("Total Expense: $%.2f\n", service.getTotalExpense());
        System.out.printf("Balance: $%.2f\n", service.getBalance());
    }

    private int getIntInput(String prompt){
        System.out.print(prompt);
        try{
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e){
            System.err.println("Invalid input. Please enter a number.");
            return -1;
        }
    }
}

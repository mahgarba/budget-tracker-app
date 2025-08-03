package budgettracker;

import budgettracker.service.TransactionService;
import budgettracker.ui.ConsoleUI;

import java.util.Scanner;

public class BudgetTracker {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        TransactionService service = new TransactionService();
        ConsoleUI ui = new ConsoleUI(scanner, service);

        ui.start();
        scanner.close();
    }
}

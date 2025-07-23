package budgettracker.ui;

import budgettracker.service.TransactionService;

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

        }
    }

}

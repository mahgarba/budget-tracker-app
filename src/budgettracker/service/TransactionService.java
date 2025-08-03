package budgettracker.service;

import budgettracker.model.Category;
import budgettracker.model.Expense;
import budgettracker.model.Income;
import budgettracker.model.Transaction;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionService {

    private List<Transaction> transactions;
    private static final String FILE_PATH = "transactions.txt";

    public TransactionService(){
        transactions = new ArrayList<>();
        loadTransactions();
    }

    public void addIncome(LocalDate date, String description, double amount, Category category){
        transactions.add(new Income(date, description, amount, category));
        saveTransactions();
    }

    public void addExpense(LocalDate date, String description, double amount, Category category){
        transactions.add(new Expense(date, description, amount, category));
        saveTransactions();
    }

    public List<Transaction> getTransactions(){
        return transactions;
    }

    public double getTotalIncome(){
        return transactions.stream()
                .filter(t -> t instanceof Income)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getTotalExpense(){
        return transactions.stream()
                .filter(t -> t instanceof Expense)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getBalance(){
        return getTotalIncome() - getTotalExpense();
    }

    private void saveTransactions() {
       try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))){

           for (Transaction t : transactions){
               String type = t instanceof  Income ? "INCOME" : "EXPENSE";
               writer.write(String.format("%s, %s, %s, %.2f, %s\n",
                       type, t.getDate(), t.getDescription(), t.getAmount(), t.getCategory().getName()));
           }
       } catch (IOException e){
           System.err.println("Error saving transactions: " + e.getMessage());
       }
    }

    private void loadTransactions(){
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){

            String line;

            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");

                if (parts.length == 5){
                    String type = parts[0];
                    LocalDate date = LocalDate.parse(parts[1].trim());
                    String description = parts[2];
                    double amount = Double.parseDouble(parts[3]);
                    Category category = new Category(parts[4]);

                    if (type.equals("INCOME")){
                        transactions.add(new Income(date, description, amount, category));
                    } else if (type.equals("EXPENSE")){
                        transactions.add(new Expense(date, description, amount, category));
                    }
                }
            }
        } catch (FileNotFoundException e){
            //File does not exist yet so start with empty list
        } catch (IOException e){
            System.err.println("Error loading transactions: " + e.getMessage());
        }
    }
}

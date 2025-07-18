package budgettracker.model;

import java.time.LocalDate;

public class Expense extends Transaction{
    public Expense(LocalDate date, String description, double amount, Category category) {
        super(date, description, amount, category);
    }
}

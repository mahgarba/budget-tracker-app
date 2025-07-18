package budgettracker.model;

import java.time.LocalDate;

public class Income extends Transaction{
    public Income(LocalDate date, String description, double amount, Category category) {
        super(date, description, amount, category);
    }
}

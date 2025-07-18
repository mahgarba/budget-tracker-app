package budgettracker.model;

import java.time.LocalDate;

public abstract class Transaction {

    private LocalDate date;
    private String description;
    private double amount;
    private Category category;

    public Transaction(LocalDate date, String description, double amount, Category category) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | N%.2f | %s", date, description, amount, category.getName());
    }
}

package budgettracker.service;

import budgettracker.model.Transaction;

import java.util.List;

public class TransactionService {

    private List<Transaction> transactions;
    private static final String FILE_PATH = "transactions.txt";
}

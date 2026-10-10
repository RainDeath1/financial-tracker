package ru.productstar.service;

import org.springframework.stereotype.Service;
import ru.productstar.model.Transaction;
import ru.productstar.model.TransactionType;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private final List<Transaction> transactions = new ArrayList<>();

    public TransactionService() {
        transactions.add(
                new Transaction("salary", 100, TransactionType.INCOME)
        );

        transactions.add(
                new Transaction("rent", 30, TransactionType.EXPENSE)
        );
    }

    public List<Transaction> getAll() {
        return transactions;
    }

    public void addExpense(String name, int sum) {
        transactions.add(
                new Transaction(name, sum, TransactionType.EXPENSE)
        );
    }

    public void addIncome(String name, int sum) {
        transactions.add(
                new Transaction(name, sum, TransactionType.INCOME)
        );
    }

    public int getBalance() {
        return transactions.stream()
                .mapToInt(transaction ->
                        transaction.getType() == TransactionType.INCOME
                                ? transaction.getSum()
                                : -transaction.getSum()
                )
                .sum();
    }
}
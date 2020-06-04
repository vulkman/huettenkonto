package de.thw.oofb.repos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import de.thw.oofb.models.Purpose;
import de.thw.oofb.models.Transaction;

public class TransactionRepo {
    
    // TODO implement actual repo
    public List<Transaction> getTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        transactions.add(Transaction.builder().timestamp(LocalDateTime.now()).purpose(Purpose.DRINKS).amount(-5.9).build());
        transactions.add(Transaction.builder().timestamp(LocalDateTime.now()).purpose(Purpose.FOOD).amount(-3.0).build());
        transactions.add(Transaction.builder().timestamp(LocalDateTime.now()).purpose(Purpose.DEPOSIT).amount(20.0).build());

        return transactions;
    }

}
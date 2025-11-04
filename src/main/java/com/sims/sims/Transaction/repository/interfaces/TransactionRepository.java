package com.sims.sims.Transaction.repository.interfaces;

import java.util.List;
import com.sims.sims.Transaction.entities.Transaction;
import com.sims.sims.shared.dtos.ResponseCreateTransactionWithService;

public interface TransactionRepository {
    void topUpWithTransaction(Long userId, Double amount, String invoiceNumber);
    List<Transaction> getTransactionHistory(long userId, long limit, long offset);
    ResponseCreateTransactionWithService createTransactionaUsingService(long userId, String invoiceNumber, String serviceCode);
}

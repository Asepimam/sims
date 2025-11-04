package com.sims.sims.Transaction.service.impl;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sims.sims.Member.entities.User;
import com.sims.sims.Member.repositories.interfaces.UserRepository;
import com.sims.sims.Transaction.entities.Transaction;
import com.sims.sims.Transaction.repository.interfaces.TransactionRepository;
import com.sims.sims.Transaction.repository.interfaces.WalletRepository;
import com.sims.sims.Transaction.service.interfaces.TransactionService;
import com.sims.sims.shared.dtos.CreateTransactionDto;
import com.sims.sims.shared.dtos.ResponseCreateTransactionWithService;
import com.sims.sims.shared.dtos.ResponsesHistoryTransaction;
import com.sims.sims.shared.dtos.UpdateAmountDto;
import com.sims.sims.shared.dtos.WalletResponseDto;
import com.sims.sims.shared.utils.InvoiceNumber;

import jakarta.transaction.Transactional;

@Service
public class TransactionServiceImpl  implements TransactionService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<ResponsesHistoryTransaction> getTransactionHistory(long limit, long offset) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (limit <=0 ) {
            limit = 10;
        }
        if (offset < 0 ) {
            offset = 0;
        }
        if (limit > 100) {
            limit = 100;
        }
        List<Transaction> transactions = transactionRepository.getTransactionHistory(user.getId(), limit, offset);
        return transactions.stream()
                .map(tx -> new ResponsesHistoryTransaction(tx.getInvoiceNumber(), tx.getTransactionType(), tx.getDescriptions(),tx.getTotalAmount(), tx.getCreateAt()))
                .toList();
    }

     @Override
    @Transactional(rollbackOn = Exception.class)
    public WalletResponseDto topUpBalance(UpdateAmountDto updateAmountDto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        String invoiceNumber = InvoiceNumber.generatedInvoiceNumber();

        transactionRepository.topUpWithTransaction(
            user.getId(), 
            updateAmountDto.getTopUpAmount(), 
            invoiceNumber
        );
        return new WalletResponseDto(walletRepository.findByUserId(user.getId()).getBalance());
    }
    
    @Override
    @Transactional(rollbackOn = Exception.class)
    public ResponseCreateTransactionWithService createTransactionUsingService(CreateTransactionDto createTransactionDto) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        String invoiceNumber = InvoiceNumber.generatedInvoiceNumber();
        ResponseCreateTransactionWithService transaction = transactionRepository.createTransactionaUsingService(
            user.getId(),
            invoiceNumber,
            createTransactionDto.getServiceCode()
        );
        return transaction;
    }

}

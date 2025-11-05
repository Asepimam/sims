package com.sims.sims.Transaction.repository.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sims.sims.Transaction.entities.Transaction;
import com.sims.sims.Transaction.repository.interfaces.TransactionRepository;
import com.sims.sims.shared.dtos.ResponseCreateTransactionWithService;
import com.sims.sims.shared.exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Modifying
    @Transactional
    public void topUpWithTransaction(Long userId, Double amount, String invoiceNumber) {
        int updatedWallets = jdbcTemplate.update(
            "UPDATE wallets SET balance = balance + ?, updated_at = NOW() WHERE user_id = ?",
            amount, userId
        );
        if (updatedWallets == 0) {
            throw new RuntimeException("Wallet not found for user: " + userId);
        }
        try {
            jdbcTemplate.update(
                "INSERT INTO transactions (user_id, invoice_number, service_code, transaction_type, description, total_amount, created_at) " +
                "VALUES (?, ?, NULL, ?, ?, ?, NOW())",
                userId, invoiceNumber, "TOPUP", "Top up balance", amount
            );
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("Invoice number already exists: " + invoiceNumber, e);
        }
    }

    @Override
    public List<Transaction> getTransactionHistory(long userId, long limit, long offset) {
        String sql = "SELECT * FROM transactions WHERE user_id = ? LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Transaction transaction = new Transaction();
            transaction.setId(rs.getLong("id"));
            transaction.setUserId(rs.getLong("user_id"));
            transaction.setInvoiceNumber(rs.getString("invoice_number"));
            transaction.setServiceCode(rs.getString("service_code"));
            transaction.setTransactionType(rs.getString("transaction_type"));
            transaction.setDescriptions(rs.getString("description"));
            transaction.setTotalAmount(rs.getDouble("total_amount"));
            transaction.setCreateAt(rs.getObject("created_at", LocalDateTime.class));
            return transaction;
        }, userId, (int) limit, (int) offset); 
    }
    

    @Override
    @Transactional
    public ResponseCreateTransactionWithService createTransactionaUsingService(long userId, String invoiceNumber, String serviceCode) {
        //get tarif
        String getTarifByServiceCode = "SELECT service_tarif from services where service_code = ?";
        Double serviceTarif = jdbcTemplate.queryForObject(getTarifByServiceCode, Double.class, serviceCode);
        
        //validate balance
        String getBalanceByUserId = "SELECT balance FROM wallets WHERE user_id = ?";
        Double userBalance = jdbcTemplate.queryForObject(getBalanceByUserId, Double.class, userId);
        if (userBalance == null || userBalance < serviceTarif) {
            throw new ResourceNotFoundException("wallet is empty ");
        }

        //update balance
        String updateBalanceByUserId = "UPDATE wallets SET balance = balance - ? WHERE user_id = ?";
        int updatedRows = jdbcTemplate.update(updateBalanceByUserId, serviceTarif, userId);

        if (updatedRows == 0) {
            throw new ResourceNotFoundException("wallet is empty ");
        }

        try {
        String insertTransaction = "INSERT INTO transactions (user_id, invoice_number, service_code, transaction_type, description, total_amount, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, NOW())";
        int rowsAffected = jdbcTemplate.update(insertTransaction, userId, invoiceNumber, serviceCode, "PAYMENT", "Service transaction", serviceTarif);

        if (rowsAffected == 0) {
            throw new ResourceNotFoundException("Failed to create transaction");
        }

        String getTransWithService = """
                SELECT s.service_code, s.service_name, t.invoice_number, t.total_amount, t.created_at
                FROM transactions t
                JOIN services s ON t.service_code = s.service_code
                WHERE t.user_id = ? AND t.invoice_number = ?
                """;
        
        return jdbcTemplate.queryForObject(getTransWithService, (rs, rowNum) -> {
            return new ResponseCreateTransactionWithService(
                rs.getString("invoice_number"),
                rs.getString("service_code"),
                rs.getString("service_name"),
                rs.getDouble("total_amount"),
                rs.getObject("created_at", LocalDateTime.class)
            );
        }, userId, invoiceNumber);

        } catch (DuplicateKeyException e) {
            throw new RuntimeException("Invoice number already exists: " + invoiceNumber, e);
        }
    }
}

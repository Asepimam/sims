package com.sims.sims.Transaction.repository.Impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sims.sims.Transaction.entities.Wallet;
import com.sims.sims.Transaction.repository.interfaces.WalletRepository;
import com.sims.sims.shared.dtos.UpdateAmountDto;

@Repository
public class WalletRepositoryImpl implements WalletRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Wallet createWallet(Long userId) {
      String sql = "INSERT INTO wallets (user_id, balance, created_at, updated_at) VALUES (?, ?, ?, ?) RETURNING *";
      LocalDateTime now = LocalDateTime.now();
      return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
         Wallet wallet = new Wallet();
         wallet.setId(rs.getLong("id"));
         wallet.setUserId(rs.getString("user_id"));
         wallet.setBalance(rs.getDouble("balance"));
         wallet.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
         wallet.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));
         return wallet;
      }, userId, 0.0, now, now);
    }
    
    @Override
    public Wallet findByUserId(long userId) {
       String sql = "SELECT * from wallets where user_id = ?";
         return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Wallet wallet = new Wallet();
            wallet.setId(rs.getLong("id"));
            wallet.setUserId(rs.getString("user_id"));
            wallet.setBalance(rs.getDouble("balance"));
            wallet.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            wallet.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));
            return wallet;
         }, userId
         );
    }

    @Override
      public void updateWallet(Long userId, UpdateAmountDto updateAmountDto) {
         String sql = "UPDATE wallets SET balance = balance + ?, updated_at = ? WHERE user_id = ?";
         LocalDateTime now = LocalDateTime.now();
         jdbcTemplate.update(sql, updateAmountDto.getTopUpAmount(), now, userId);
      }
}

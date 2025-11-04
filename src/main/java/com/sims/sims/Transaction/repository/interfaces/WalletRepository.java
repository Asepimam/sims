package com.sims.sims.Transaction.repository.interfaces;
import com.sims.sims.Transaction.entities.Wallet;
import com.sims.sims.shared.dtos.UpdateAmountDto;

public interface WalletRepository {
    Wallet createWallet(Long userId);
    Wallet findByUserId(long userId);
    void updateWallet(Long userId, UpdateAmountDto updateAmountDto);
}

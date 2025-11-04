package com.sims.sims.Transaction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sims.sims.Member.entities.User;
import com.sims.sims.Member.repositories.interfaces.UserRepository;
import com.sims.sims.Transaction.entities.Wallet;
import com.sims.sims.Transaction.repository.interfaces.WalletRepository;
import com.sims.sims.Transaction.repository.interfaces.TransactionRepository;
import com.sims.sims.Transaction.service.interfaces.WalletService;
import com.sims.sims.shared.dtos.UpdateAmountDto;
import com.sims.sims.shared.dtos.WalletResponseDto;
import com.sims.sims.shared.utils.InvoiceNumber;

import jakarta.transaction.Transactional;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public WalletResponseDto getBalance() {
        // validate user
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        //get wallet
        Wallet wallet = walletRepository.findByUserId(user.getId());
        if (wallet == null) {
            throw new RuntimeException("Wallet not found");
        }
        return new WalletResponseDto(wallet.getBalance());
    }
}

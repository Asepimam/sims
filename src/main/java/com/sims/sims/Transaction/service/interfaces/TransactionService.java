package com.sims.sims.Transaction.service.interfaces;

import java.util.List;

import com.sims.sims.shared.dtos.CreateTransactionDto;
import com.sims.sims.shared.dtos.ResponseCreateTransactionWithService;
import com.sims.sims.shared.dtos.ResponsesHistoryTransaction;
import com.sims.sims.shared.dtos.UpdateAmountDto;
import com.sims.sims.shared.dtos.WalletResponseDto;

public interface TransactionService {
    List<ResponsesHistoryTransaction> getTransactionHistory(long limit, long offset);
    WalletResponseDto topUpBalance(UpdateAmountDto  updateAmountDto);
    ResponseCreateTransactionWithService createTransactionUsingService(CreateTransactionDto createTransactionDto);

}

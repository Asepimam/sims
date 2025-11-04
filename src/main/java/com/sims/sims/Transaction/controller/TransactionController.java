package com.sims.sims.Transaction.controller;

import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sims.sims.Transaction.service.interfaces.TransactionService;
import com.sims.sims.Transaction.service.interfaces.WalletService;
import com.sims.sims.shared.dtos.CreateTransactionDto;
import com.sims.sims.shared.dtos.ResponseCreateTransactionWithService;
import com.sims.sims.shared.dtos.ResponseDto;
import com.sims.sims.shared.dtos.ResponsesHistoryTransaction;
import com.sims.sims.shared.dtos.UpdateAmountDto;
import com.sims.sims.shared.dtos.WalletResponseDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired 
    private WalletService walletService;
    @Autowired
    private TransactionService TrService;
    
    @GetMapping("/balance")
    public ResponseEntity<ResponseDto> getBalance() {
        WalletResponseDto walletResponse = walletService.getBalance();
        return ResponseEntity.ok(new ResponseDto(0, "Get Balance Berhasil", Map.of("balance", walletResponse.getBalance())));
    }

    @PostMapping("/topup")
    public ResponseEntity<ResponseDto> topUp(@Valid @RequestBody UpdateAmountDto request) {
        WalletResponseDto walletResponse = TrService.topUpBalance(request);
        return ResponseEntity.ok(new ResponseDto(0, "Top Up Berhasil", Map.of("balance", walletResponse.getBalance())));
    }

    @PostMapping("/transaction")
    public ResponseEntity<ResponseDto> createTransaction(@Valid @RequestBody CreateTransactionDto request) {
        ResponseCreateTransactionWithService response = TrService.createTransactionUsingService(request);
        return ResponseEntity.ok(new ResponseDto(0, "Transaction Created Successfully",response));
    }

    @GetMapping("/transaction/history")
    public ResponseEntity<ResponseDto> getTransactionHistory(@RequestParam long limit, @RequestParam long offset) {
        List<ResponsesHistoryTransaction> history = TrService.getTransactionHistory(limit, offset);
        return ResponseEntity.ok(new ResponseDto(0, "Success", Map.of("limit", limit, "offset", offset, "records", history)));
    }
}
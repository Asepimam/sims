package com.sims.sims.Transaction.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sims.sims.shared.dtos.ResponseDto;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @GetMapping("/balance")
    public ResponseEntity<ResponseDto> getBalance() {
        // Implementation here  
        return ResponseEntity.ok(new ResponseDto(0, "Success", 1000));
    }

    @PostMapping("/topup")
    public ResponseEntity<ResponseDto> topUp(@RequestBody String request) {
        // Implementation here
        return ResponseEntity.ok(new ResponseDto(0, "Success", 1000));
    }

    @PostMapping("/transaction")
    public ResponseEntity<ResponseDto> createTransaction(@RequestBody String request) {
        // Implementation here
        return ResponseEntity.ok(new ResponseDto(0, "Success", 1000));
    }

    @GetMapping("/transaction/history")
    public ResponseEntity<ResponseDto> getTransactionHistory() {
        // Implementation here
        return ResponseEntity.ok(new ResponseDto(0, "Success", 1000));
    }
}
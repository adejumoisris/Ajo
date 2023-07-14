package com.AjoPay.AjoPay.controller;

import com.AjoPay.AjoPay.model.Account;
import com.AjoPay.AjoPay.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    @GetMapping("/{}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId){
        Account account = accountService.getAccountById(accountId);
        return ResponseEntity.ok(account);
    }
}

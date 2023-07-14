package com.AjoPay.AjoPay.service.serviceImplementation;

import com.AjoPay.AjoPay.exceptions.AccountNotFoundException;
import com.AjoPay.AjoPay.exceptions.CustomException;
import com.AjoPay.AjoPay.model.Account;
import com.AjoPay.AjoPay.repository.AccountRepository;
import com.AjoPay.AjoPay.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public Account depositMoney(Long accountId, BigDecimal depositAmount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(()-> new AccountNotFoundException("Account not Found with ID" + accountId));

        BigDecimal newBalance =account.getBalance().add(depositAmount);
        account.setBalance(newBalance);
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountById(Long accountId) {
        return null;
    }
}

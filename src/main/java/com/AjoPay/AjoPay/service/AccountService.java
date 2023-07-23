package com.AjoPay.AjoPay.service;

import com.AjoPay.AjoPay.model.Account;

import java.math.BigDecimal;

public interface AccountService {
    public Account depositMoney(Long accountId, BigDecimal depositAmount);

    Account getAccountById( Long accountId);

}

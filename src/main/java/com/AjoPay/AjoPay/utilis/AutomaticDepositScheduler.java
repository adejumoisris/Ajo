package com.AjoPay.AjoPay.utilis;

import com.AjoPay.AjoPay.model.AutomaticDeposition;
import com.AjoPay.AjoPay.service.AccountService;
import com.AjoPay.AjoPay.service.AutomaticDepositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


@Component
@RequiredArgsConstructor
public class AutomaticDepositScheduler {
    private final AccountService accountService;
    private final AutomaticDepositionService automaticDepositionService;

    @Scheduled(cron = "0001*60") // Run at midnight on  the first day of every month
    public void performAutomaticDeposition(){
        List<AutomaticDeposition> automaticDepositions = automaticDepositionService.getAllAutomaticDeposits();
        for (AutomaticDeposition automaticDeposition : automaticDepositions){
            Long accountId = automaticDeposition.getAccountId();
            BigDecimal depositAmount = automaticDeposition.getDepositAmount();

            accountService.depositMoney(accountId,depositAmount);
        }
    }

}

package com.AjoPay.AjoPay.controller;

import com.AjoPay.AjoPay.model.AutomaticDeposition;
import com.AjoPay.AjoPay.service.AutomaticDepositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/automatic-deposit")
@RequiredArgsConstructor
public class AutomaticDepositController {
    private final AutomaticDepositionService automaticDepositionService;
    @GetMapping("/deposit")
    public ResponseEntity<List<AutomaticDeposition>> getAllAutomaticDepositions(){
        List<AutomaticDeposition> automaticDepositions = automaticDepositionService.getAllAutomaticDeposits();
        return ResponseEntity.ok(automaticDepositions);
    }
    // 08144253521
}

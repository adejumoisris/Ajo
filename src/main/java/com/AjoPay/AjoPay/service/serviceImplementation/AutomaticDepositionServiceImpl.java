package com.AjoPay.AjoPay.service.serviceImplementation;

import com.AjoPay.AjoPay.model.AutomaticDeposition;
import com.AjoPay.AjoPay.repository.AutomaticDepositionRepository;
import com.AjoPay.AjoPay.service.AutomaticDepositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutomaticDepositionServiceImpl implements AutomaticDepositionService {
    private final AutomaticDepositionRepository automaticDepositionRepository;

    public List<AutomaticDeposition> getAllAutomaticDeposits(){
        return automaticDepositionRepository.findAll();
    }
}

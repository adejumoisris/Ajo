package com.AjoPay.AjoPay.repository;

import com.AjoPay.AjoPay.model.AutomaticDeposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomaticDepositionRepository extends JpaRepository<AutomaticDeposition, Long> {
}

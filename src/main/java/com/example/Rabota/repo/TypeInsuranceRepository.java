package com.example.Rabota.repo;

import com.example.Rabota.Models.TypeInsurance;
import org.springframework.data.repository.CrudRepository;

public interface TypeInsuranceRepository extends CrudRepository<TypeInsurance, Long> {
    TypeInsurance findByNameTypeInsurance(String nameTypeInsurance);
}

package com.example.Rabota.repo;

import com.example.Rabota.Models.KPP;
import org.springframework.data.repository.CrudRepository;

public interface KppRepository extends CrudRepository<KPP, Long> {

    KPP findByTypeKpp(String typeKpp);
}

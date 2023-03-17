package com.example.Rabota.repo;

import com.example.Rabota.Models.Buyer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuyerRepository extends CrudRepository<Buyer,Long> {

    Buyer findBySurnameB(String surnameB);

    Buyer findByINN(String INN);
}

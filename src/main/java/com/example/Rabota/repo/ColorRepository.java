package com.example.Rabota.repo;

import com.example.Rabota.Models.Color;

import org.springframework.data.repository.CrudRepository;

public interface ColorRepository extends CrudRepository<Color, Long> {

    Color findByColourrr(String colourrr);
}

package com.example.Rabota.repo;


import com.example.Rabota.Models.Manager;
import com.example.Rabota.Models.Salon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SalonRepository extends CrudRepository<Salon,Long> {

    List<Salon> findByNameSalon(String nameSalon);
    List<Salon> findByNameSalonContains (String nameSalon);

}

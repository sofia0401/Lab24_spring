package com.example.demo.repos;

import com.example.demo.models.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    List<Phone> findAllByOrderByIdAsc();
    List<Phone> findAllByOrderByNameAsc();
    List<Phone> findAllByOrderByCreationYearAsc();
}

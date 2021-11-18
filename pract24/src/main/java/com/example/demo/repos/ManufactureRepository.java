package com.example.demo.repos;

import com.example.demo.models.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ManufactureRepository extends JpaRepository<Manufacture, Long> {
    List<Manufacture> findAllByOrderByIdAsc();
    List<Manufacture> findAllByOrderByNameAsc();
    List<Manufacture> findAllByOrderByAddressAsc();
}

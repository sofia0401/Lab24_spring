package com.example.demo.DAO;

import com.example.demo.models.Manufacture;
import com.example.demo.models.Phone;
import com.example.demo.repos.ManufactureRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@Transactional

public class ManufactureDAO {
    private ManufactureRepository manufactureRepo;

    @Autowired
    public ManufactureDAO(ManufactureRepository manufactureRepo) {
        this.manufactureRepo = manufactureRepo;
    }


    public Set<Phone> getPhoneByManufacture(Long id) {
        log.info("Get phones that were created by this manufacture");
        return manufactureRepo.findById(id).orElseThrow(() ->
                new IllegalStateException("Manufacture with this id not found"))
                .getPhoneSet();
    }

    public List<Manufacture> getManufactures() {
        log.info("Get all manufactures");

        return manufactureRepo.findAll();
    }


    public Manufacture show(Long id) {
        log.info("Show manufacture");
        return manufactureRepo.findById(id).get();
    }

    public void save(Manufacture manufacture) {
        log.info("Save manufacture");
        manufactureRepo.save(manufacture);
    }

    public void delete(Long id) {
        log.info("Delete manufacture");
        manufactureRepo.deleteById(id);
    }
    public List<Manufacture> getManufactureById() {
        log.info("Get manufacture list filtered by Id");
        return manufactureRepo.findAllByOrderByIdAsc();
    }

    public List<Manufacture> getManufactureByName() {
        log.info("Get manufacture list filtered by Name");
        return manufactureRepo.findAllByOrderByNameAsc();
    }
    public List<Manufacture> getManufactureByAddress() {
        log.info("Get manufacture list filtered by Address");
        return manufactureRepo.findAllByOrderByAddressAsc();
    }
}
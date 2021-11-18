package com.example.demo.DAO;

import com.example.demo.models.Manufacture;
import com.example.demo.models.Phone;
import com.example.demo.repos.PhoneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class PhoneDAO {
    private PhoneRepository phoneRepo;

    @Autowired
    public PhoneDAO(PhoneRepository phoneRepo) {
        this.phoneRepo = phoneRepo;
    }

    public Manufacture getManufactureByPhone(Long id) {
        log.info("Get manufacture which has created this phone");
        return phoneRepo.findById(id).orElseThrow(() ->
                new IllegalStateException("Phone with this id not found"))
                .getManufacture();
    }

    public List<Phone> getPhones() {
        log.info("Get all phones");
        return phoneRepo.findAll();
    }


    public Phone show(Long id) {
        log.info("Show phone");
        return phoneRepo.findById(id).get();
    }

    public void save(Phone phone) {
        log.info("Save phone");
        phoneRepo.save(phone);
    }

    public void delete(Long id) {
        log.info("Delete phone");
        phoneRepo.deleteById(id);
    }
    public List<Phone> getPhoneById() {
        log.info("Get phones list filtered by Id");
        return phoneRepo.findAllByOrderByIdAsc();
    }

    public List<Phone> getPhoneByName() {
        log.info("Get phones list filtered by Name");
        return phoneRepo.findAllByOrderByNameAsc();
    }
    public List<Phone> getPhoneByYear() {
        log.info("Get phones list filtered by Creation Year");
        return phoneRepo.findAllByOrderByCreationYearAsc();
    }
}
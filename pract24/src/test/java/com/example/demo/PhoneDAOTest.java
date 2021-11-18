package com.example.demo;

import com.example.demo.DAO.PhoneDAO;
import com.example.demo.models.Phone;
import com.example.demo.repos.PhoneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PhoneDAOTest {
    @Mock
    private PhoneRepository phoneRepo;
    @Captor
    ArgumentCaptor<Phone> captor;
    @Test
    void getPhones() {
        Phone phone1 = new Phone();
        phone1.setName("IPhone XS");
        Phone phone2 = new Phone();
        phone2.setName("Samsung Galaxy A71");
        Mockito.when(phoneRepo.findAll()).thenReturn(List.of(phone1,phone2));
        PhoneDAO phoneDAO = new PhoneDAO(phoneRepo);

        Assertions.assertEquals(2,phoneDAO.getPhones().size());
        Assertions.assertEquals("Samsung Galaxy A71",phoneDAO.getPhones().get(1).getName());

    }
    @Test
    void saveOrUpdate() {
        Phone phone = new Phone();
        phone.setName("IPhone 12 Pro");
        PhoneDAO phoneDAO = new PhoneDAO(phoneRepo);

        phoneDAO.save(phone);
        Mockito.verify(phoneRepo).save(captor.capture());
        Phone captured = captor.getValue();
        Assertions.assertEquals("IPhone 12 Pro", captured.getName());

    }

    @Test
    void test(){
        int x= 7;
        int y=3;
        Assertions.assertEquals(10,x+y);
    }
}

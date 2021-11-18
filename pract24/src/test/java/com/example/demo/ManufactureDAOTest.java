package com.example.demo;

import com.example.demo.DAO.ManufactureDAO;
import com.example.demo.models.Manufacture;
import com.example.demo.repos.ManufactureRepository;
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
public class ManufactureDAOTest {
    @Mock
    private ManufactureRepository manufactureRepo;
    @Captor
    ArgumentCaptor<Manufacture> captor;
    @Test
    void getManufactures() {
        Manufacture manufacture1 = new Manufacture();
        manufacture1.setName("Samsung");
        Manufacture manufacture2 = new Manufacture();
        manufacture2.setName("Apple");
        Mockito.when(manufactureRepo.findAll()).thenReturn(List.of(manufacture1,manufacture2));
        ManufactureDAO manufactureDAO = new ManufactureDAO(manufactureRepo);

        Assertions.assertEquals(2,manufactureDAO.getManufactures().size());
        Assertions.assertEquals("Samsung",manufactureDAO.getManufactures().get(0).getName());

    }
    @Test
    void saveOrUpdate() {
        Manufacture manufacture = new Manufacture();
        manufacture.setName("Apple");
        ManufactureDAO manufactureDAO = new ManufactureDAO(manufactureRepo);

        manufactureDAO.save(manufacture);
        Mockito.verify(manufactureRepo).save(captor.capture());
        Manufacture captured = captor.getValue();
        Assertions.assertEquals("Apple", captured.getName());

    }
}

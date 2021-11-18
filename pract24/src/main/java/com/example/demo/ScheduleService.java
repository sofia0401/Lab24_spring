package com.example.demo;

import com.example.demo.DAO.ManufactureDAO;
import com.example.demo.DAO.PhoneDAO;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

@Service
public class ScheduleService {
    private String phoneFilePath = "src/main/resources/DataBase/phoneDB.txt";
    private String manufactureFilePath = "src/main/resources/DataBase/manufactureDB.txt";

    @Autowired
    PhoneDAO phoneDAO;
    @Autowired
    ManufactureDAO manufactureDAO;


//    @Scheduled (cron = "*/10 * * * * *")
    @Scheduled (cron = "* */30 * * * *")

    @SneakyThrows
    public void cleanDirPrintDB()  {

        File filePhone = new File(phoneFilePath);
        File fileManufacture = new File(manufactureFilePath);
        filePhone.delete();
        fileManufacture.delete();

        FileOutputStream outputStream=new FileOutputStream(phoneFilePath);
        outputStream.write(phoneDAO.getPhones().toString().getBytes(StandardCharsets.UTF_8));
        outputStream= new FileOutputStream(manufactureFilePath);
        outputStream.write(manufactureDAO.getManufactures().toString().getBytes(StandardCharsets.UTF_8));


    }
}

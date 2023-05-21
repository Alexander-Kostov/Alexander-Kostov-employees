package com.example.alexanderkostovemployees.configuration;

import com.example.alexanderkostovemployees.database.DatabaseImpl;
import com.example.alexanderkostovemployees.io.CsvFileReader;
import com.example.alexanderkostovemployees.service.EmployeeServiceImpl;
import com.example.alexanderkostovemployees.web.EmployeesViewController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigurationClass {
    @Bean
    public DatabaseImpl database() {
        return new DatabaseImpl();
    }

    @Bean
    public CsvFileReader csvFileReader(DatabaseImpl database) {
        return new CsvFileReader(database);
    }

}

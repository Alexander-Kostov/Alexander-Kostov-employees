package com.example.alexanderkostovemployees.database;

import com.example.alexanderkostovemployees.model.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class DatabaseImpl implements Database {
    private static final List<Employee> employees = new ArrayList<>();
    @Override
    public void saveAllToDatabase(List<Employee> allEmployees) {
        employees.addAll(allEmployees);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }
}

package com.example.alexanderkostovemployees.database;

import com.example.alexanderkostovemployees.model.Employee;

import java.util.List;

public interface Database {

    void saveAllToDatabase(List<Employee> employees);

    List<Employee> getAllEmployees();
}

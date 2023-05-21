package com.example.alexanderkostovemployees.service;


import com.example.alexanderkostovemployees.model.Employee;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {

    void saveEmployees(List<Employee> employees) throws IOException;

    List<Employee> getEmployees();

}

package com.example.alexanderkostovemployees.service;

import com.example.alexanderkostovemployees.database.DatabaseImpl;
import com.example.alexanderkostovemployees.io.CsvFileReader;
import com.example.alexanderkostovemployees.model.Employee;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final CsvFileReader csvFileReader;

    private final DatabaseImpl database;

    public EmployeeServiceImpl(CsvFileReader csvFileReader, DatabaseImpl database) {
        this.csvFileReader = csvFileReader;
        this.database = database;
    }

    @Override
    public void saveEmployees(List<Employee> employees) throws IOException {
        List<Employee> allEmployees = this.csvFileReader.getEmployees();
        this.database.saveAllToDatabase(allEmployees);
    }

    @Override
    public List<Employee> getEmployees() {
        return this.database.getAllEmployees();
    }

    public List<String> findLongestDurationPair() {
        List<Employee> employees = getEmployees();

        List<String> longestDurationPair = new ArrayList<>();

        int longestDuration = 0;

        for (int i = 0; i < employees.size(); i++) {
            Employee employee1 = employees.get(i);
            for (int j = i + 1; j < employees.size(); j++) {
                Employee employee2 = employees.get(j);
                if (employee1.getProjectId().equals(employee2.getProjectId())) {
                    int duration = calculateDurationInDays(employee1, employee2);
                    if (duration > longestDuration) {
                        longestDuration = duration;
                        longestDurationPair.clear();
                        longestDurationPair.add(String.valueOf(employee1.getId()));
                        longestDurationPair.add(String.valueOf(employee2.getId()));
                        longestDurationPair.add(String.valueOf(employee1.getProjectId()));
                        longestDurationPair.add(String.valueOf(duration));
                    }
                }
            }
        }

        return longestDurationPair;
    }


    private int calculateDurationInDays(Employee employee1, Employee employee2) {
        LocalDate startDate = employee1.getDateFrom().isAfter(employee2.getDateFrom())
                ? employee1.getDateFrom() : employee2.getDateFrom();
        LocalDate endDate1 = employee1.getDateTo() != null ? employee1.getDateTo() : LocalDate.now();
        LocalDate endDate2 = employee2.getDateTo() != null ? employee2.getDateTo() : LocalDate.now();
        LocalDate endDate = endDate1.isBefore(endDate2) ? endDate1 : endDate2;
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }

}

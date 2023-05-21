package com.example.alexanderkostovemployees.io;

import com.example.alexanderkostovemployees.database.DatabaseImpl;
import com.example.alexanderkostovemployees.model.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.example.alexanderkostovemployees.constants.AppConstants.DATABASE_FILE_PATH;
import static com.example.alexanderkostovemployees.constants.AppConstants.INVALID_PARSE_OF_DATE;

public class CsvFileReader {
    private final DatabaseImpl database;

    public CsvFileReader(DatabaseImpl database) {
        this.database = database;
    }

    @PostConstruct
    public List<Employee> getEmployees() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(DATABASE_FILE_PATH);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        List<Employee> employees = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] csvData = line.split(";\\s*");
            Long employeeId = Long.parseLong(csvData[0]);
            Long projectId = Long.parseLong(csvData[1]);

            DateTimeFormatter[] formatters = {

                    DateTimeFormatter.ofPattern("d.MM.yyyy"),

                    // Number representation: 22.16.2019
                    DateTimeFormatter.ofPattern("dd.MM.yyyy"),

                    // Number representation: 20.05.2023, does not handle case with leading zero for a day
                    DateTimeFormatter.ofPattern("d.MM.yyyy"),

                    //Number representation: 7.16.2018
                    DateTimeFormatter.ofPattern("dd.MM.yyyy"),

                    // Number representation: 20-05-2023
                    DateTimeFormatter.ofPattern("dd-MM-yyyy"),

                    // Number representation: 2023/05/20
                    DateTimeFormatter.ofPattern("yyyy/MM/dd"),

                    // Number representation: 20.5.2023
                    DateTimeFormatter.ofPattern("d.M.yyyy"),

                    // Number representation: Handling cases with leading zeros for both month and day like 20.05.2023 and 02.06.2023
                    DateTimeFormatter.ofPattern("dd.MM.yyyy"),

                    // Number representation: 1.1.2009
                    DateTimeFormatter.ofPattern("d.M.uuuu")

            };

            LocalDate dateFrom = null;
            LocalDate dateTo = null;

            for (DateTimeFormatter formatter : formatters) {
                try {
                    dateFrom = LocalDate.parse(csvData[2].trim(), formatter);
                    break;
                } catch (Exception e) {
                    System.out.println(INVALID_PARSE_OF_DATE);
                }
            }

            for (DateTimeFormatter formatter : formatters) {
                if (csvData[3] == null || csvData[3].toLowerCase().equals("null")) {
                    dateTo = LocalDate.now();
                    break;
                }else {
                    try {
                        dateTo = LocalDate.parse(csvData[3], formatter);
                        break;

                    } catch (Exception e) {
                        System.out.println(INVALID_PARSE_OF_DATE);
                    }
                }
            }


            Employee employee = new Employee(employeeId, projectId, dateFrom, dateTo);
            employees.add(employee);

        }
        this.database.saveAllToDatabase(employees);

        return employees;
    }
}

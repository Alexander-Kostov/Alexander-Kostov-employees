package com.example.alexanderkostovemployees.web;

import com.example.alexanderkostovemployees.database.DatabaseImpl;
import com.example.alexanderkostovemployees.model.Employee;
import com.example.alexanderkostovemployees.service.EmployeeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EmployeesViewController {

    private final DatabaseImpl database;
    private final EmployeeServiceImpl employeeService;

    public EmployeesViewController(DatabaseImpl database, EmployeeServiceImpl employeeService) {
        this.database = database;
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String viewEmployees(Model model) {

        List<Employee> allEmployees = database.getAllEmployees();

        model.addAttribute("employees", allEmployees);
        model.addAttribute("isClicked", false);

        return "employees";
    }

    @GetMapping("/home")
    public String viewPairWithMostDays(Model model) {
        List<Employee> employees = this.database.getAllEmployees();
        List<String> longestDurationPair = this.employeeService.findLongestDurationPair();

        model.addAttribute("employees", employees);
        model.addAttribute("pairs", longestDurationPair);
        model.addAttribute("isClicked", true);
        return "employees";
    }
}

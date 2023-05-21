package com.example.alexanderkostovemployees.model;

import java.time.LocalDate;

public class Employee {

    private Long id;

    private Long projectId;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    public Employee(Long id, Long projectId, LocalDate dateFrom, LocalDate dateTo) {
        this.id = id;
        this.projectId = projectId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Long getId() {
        return id;
    }

    public Employee setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getProjectId() {
        return projectId;
    }

    public Employee setProjectId(Long projectId) {
        this.projectId = projectId;
        return this;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public Employee setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public Employee setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }
}

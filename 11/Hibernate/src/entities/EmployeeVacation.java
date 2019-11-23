package entities;

import java.time.LocalDate;

public class EmployeeVacation {
    private Integer id;
    private Employee employee;
    private LocalDate startVacation;
    private LocalDate endVacation;


    public EmployeeVacation() {
        // For Hibernate?
    }

    public EmployeeVacation(Employee employee, LocalDate startVacation, LocalDate endVacation) {

        this.employee = employee;
        this.startVacation = startVacation;
        this.endVacation = endVacation;

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getStartVacation() {
        return startVacation;
    }

    public void setStartVacation(LocalDate startVacation) {
        this.startVacation = startVacation;
    }

    public LocalDate getEndVacation() {
        return endVacation;
    }

    public void setEndVacation(LocalDate endVacation) {
        this.endVacation = endVacation;
    }


}
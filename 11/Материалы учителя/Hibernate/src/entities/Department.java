package entities;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Danya on 26.10.2015.
 */
public class Department {
    private Integer id;
    private Integer headId;
    private String name;
    private String description;
    private HashSet<Employee> employees = new HashSet<>(0);
    //================
    private HashSet<Employee> head = new HashSet<>(0);
    //================

    public Department() {
        //Used by Hibernate
    }

    public Department(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHeadId() {
        return headId;
    }

    public void setHeadId(Integer headId) {
        this.headId = headId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashSet<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees.addAll(employees);
    }
    //===========================
    public HashSet<Employee> getHead() {
        return head;
    }

    public void setHead( HashSet<Employee> head) {
        this.head.addAll(head);
    }


}

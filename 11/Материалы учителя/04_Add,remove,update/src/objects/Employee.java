package objects;

import java.sql.Date;

/**
 * Created by Danya on 26.10.2015.
 */
public class Employee
{
    private Integer id;
    private Date hireDate;
    private Integer salary;
    private String name;
    private Department department;
    private Department ledDepartment;

    public Employee(){
        //Used by Hibernate
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Integer getSalary() { return salary; }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {return department;}

    public void setDepartment(Department department) {this.department = department;}

    public Department getLedDepartment() {return ledDepartment;}

    public void setLedDepartment(Department ledDepartment) {this.ledDepartment = ledDepartment;}
}

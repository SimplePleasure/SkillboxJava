package objects;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Danya on 26.10.2015.
 */
public class Department
{
    private Integer id;
    //private Integer headId;
    private Employee headEmployee;
    private String name;
    private String description;
    private Set<Employee> employees = new HashSet<>(0);

    public Department() {
        //Used by Hibernate
    }

    public Department(String name)
    {
        this.name = name;
    }


    public Employee getHeadEmployee() { return headEmployee;  }

    public void setHeadEmployee(Employee headEmployee) {this.headEmployee = headEmployee;   }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //public Integer getHeadId() {     return headId;  }

    //public void setHeadId(Integer headId) {        this.headId = headId;    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}

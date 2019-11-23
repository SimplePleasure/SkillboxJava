package objects;

import java.sql.Date;

public class Vacation {
    private Integer id;
    private Date beginDate;
    private Date endDate;
    private Employee employee;

    public Vacation(){
        // For Hibernate
    }

    public Vacation(Employee employee, Date beginDate, Date endDate){
        this.employee = employee;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


}

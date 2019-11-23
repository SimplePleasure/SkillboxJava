import objects.Department;
import objects.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
import java.sql.Date;
import java.util.List;

/**
 * Created by Danya on 26.10.2015.
 */
public class Loader {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        setUp();

        // create a couple of events...
        Session session = sessionFactory.openSession();
        session.beginTransaction();

//        List<Department> departments = (List<Department>) session.createQuery("FROM Department").list();
//        for(Department department : departments) {
//            System.out.println(department.getName());
//        }

//        Department dept = new Department("Отдел производства");
//        session.save(dept);
//        System.out.println(dept.getId());

//        Department dept = (Department) session.createQuery("FROM Department WHERE name=:name")
//            .setParameter("name", "Отдел производства").list().get(0);
//        session.delete(dept);

//        List<Employee> employees = (List<Employee>) session.createQuery(
//                "SELECT e FROM Employee e JOIN e.ledDepartment ld WHERE ld != e.department"
//        ).list();
//
//        for (Employee employee : employees) {
//            System.out.println(employee.getName()
//                    + " руководит подразделением " + employee.getLedDepartment().getName()
//                    + ", числится в подразделении " + employee.getDepartment().getName());
//        }
//        System.out.println();
//
//
//        employees = (List<Employee>) session.createQuery(
//                "SELECT e FROM Employee e JOIN e.ledDepartment ld WHERE e.salary < :salary "
//        ).setParameter("salary", 115000).list();
//
//        for (Employee employee : employees) {
//            System.out.println(employee.getName() + " \t" + employee.getSalary());
//        }
//        System.out.println();
//
//
//        employees = (List<Employee>) session.createQuery(
//                "SELECT e FROM Employee e JOIN e.ledDepartment ld WHERE MONTH(e.hireDate) BETWEEN :month1 AND :month2"
//        ).setParameter("month1", 1).setParameter("month2", 2).list();
//
//        for (Employee employee : employees) {
//            System.out.println(employee.getName() + " \t" + employee.getHireDate());
//        }
//        System.out.println();


        session.getTransaction().commit();
        session.close();

        //==================================================================
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    //=====================================================================

    private static void setUp() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(new File("src/config/hibernate.cfg.xml")) // configures settings from hibernate.config.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}

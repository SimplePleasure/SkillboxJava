
import entities.Department;
import entities.Employee;
import entities.EmployeeVacation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Danya on 26.10.2015.
 */
public class Loader {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {

        setUp();

        Session session = sessionFactory.openSession();
        session.beginTransaction();



//==============================================================================================================11.4
        
//        List<Employee> listnew = (List<Employee>) session.createQuery("SELECT e FROM Employee e").list();
//
//        for (Employee e: listnew) {
//            boolean tableIsEmpty = (Long) session.createQuery("SELECT  COUNT(*) FROM EmployeeVacation where id=:id")
//                    .setParameter("id", e.getId()).uniqueResult() == 0;
//
//            if (tableIsEmpty) {
//                LocalDate d[] = rand();
//                System.out.println(d[0] + "\t\t\t" + d[1]);
//                EmployeeVacation ev = new EmployeeVacation(e, d[0], d[1]);
//                session.save(ev);
//            }
//        }
//
//
//
//        List<Department> departments = (List<Department>) session.createQuery(
//                "SELECT d FROM Department d"
//        ).list();
//
//        String queryPart1 = "SELECT\n" +
//                "  e1.name,\n" +
//                "  v1.begin_date,\n" +
//                "  v1.end_date,\n" +
//                "  e2.name AS name2,\n" +
//                "  v2.begin_date AS begin_date2,\n" +
//                "  v2.end_date AS end_date2,\n" +
//                "  CASE WHEN v1.begin_date < v2.begin_date THEN v2.begin_date ELSE v1.begin_date END AS expr1,\n" +
//                "  CASE WHEN v1.end_date < v2.end_date THEN v1.end_date ELSE v2.end_date END AS expr2,\n" +
//                "  DATEDIFF(CASE WHEN v1.end_date < v2.end_date THEN v1.end_date ELSE v2.end_date END, \n" +
//                "  CASE WHEN v1.begin_date < v2.begin_date THEN v2.begin_date ELSE v1.begin_date END) + 1 AS expr3\n" +
//                "FROM vacation v1\n" +
//                "  INNER JOIN vacation v2\n" +
//                "    ON v1.employee <> v2.employee\n" +
//                "  INNER JOIN employee e1\n" +
//                "    ON v1.employee = e1.id\n" +
//                "  INNER JOIN employee e2\n" +
//                "    ON v2.employee = e2.id\n" +
//                "    AND e2.department_id = e1.department_id\n" +
//                "WHERE (v1.begin_date BETWEEN v2.begin_date AND v2.end_date\n" +
//                "OR v1.end_date BETWEEN v2.begin_date AND v2.end_date)\n" +
//                "AND e1.department_id = ";
//        String queryPart2 = "\nORDER BY e1.name, v1.begin_date";
//        System.out.println("\n\n");
//        for (Department department : departments) {
//            System.out.println(department.getName());
//            String depID = department.getId().toString();
//            List<Object[]> vacationOverlays = (List<Object[]>)
//                    session.createSQLQuery(queryPart1 + depID + queryPart2).list();
//            System.out.println(vacationOverlays.size() + " пересечений");
//            for (Object[] col : vacationOverlays) {
//                System.out.println(col[0] + " [" + col[1] + " : " + col[2]
//                        + "] пересекается с " + col[3] + " [" + col[4] + " : "
//                        + col[5] + "], на " + col[8] + " дней, с " + col[6] + " по " + col[7]);
//            }
//            System.out.println();
//        }


















        //===========================================================11.3 Сотрудники руководящие не своим подразделением
//        List<Employee> employees = (List<Employee>) session.createQuery(
//                "SELECT e FROM Employee e JOIN e.ledDepartment ld WHERE ld != e.department"
//        ).list();
//        for (Employee employee : employees) {
//            System.out.println(employee.getName());
//        }

        //==========================================================================================11.3 salary < 110000
//        List<Employee> employees = (List<Employee>) session.createQuery(
//                "SELECT e FROM Employee e WHERE e.salary < 110000"
//        ).list();
//        for (Employee employee : employees) {
//            System.out.println(employee.getName());
//        }


//        List<Employee> employees = (List<Employee>) session.createQuery(
//                "SELECT e FROM Employee e JOIN e.ledDepartment ld WHERE e.salary < :salary "
//        ).setParameter("salary", 115000).list();
//        for (Employee employee : employees) {
//            System.out.println(employee.getName() + " \t" + employee.getSalary());
//        }

        //=======================================================================11.3 устроились на работу до марта 2010
//        String str = "2010-02-01";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate dateTime = LocalDate.parse(str, formatter);
//
//        List <Employee> employees = session.createQuery("SELECT e FROM Employee e WHERE hireDate <:date"
//        ).setParameter("date", dateTime).list();
//        for (Employee e : employees) {
//            System.out.println(e.getName());
//        }












//        List<Employee> employees = (List<Employee>) session.createQuery("FROM Employee").list();
//        for (Employee e : employees) {
//            System.out.println(e.getName() + "\t\t\t" + e.getDepartment().getHeadEmployee().getName());
//        }

//        List<Department> departments = (List<Department>) session.createQuery("FROM Department").list();
//        System.out.println("Список отделов производства:");
//        for (Department department : departments) {
//            System.out.println(department.getHeadEmployee().getName());
//        }



        //======================================================================== Добавляет в таблицу БД новую запись.
//        Department dept = new Department("Отдел производства");
//        session.save(dept);
//        System.out.println("Сохранен отдел производства с id = " + dept.getId());

        //====================== Удаляет из таблицы department строку с названием "Отдел производства" из колонки name
//        Department dept = (Department) session.createQuery("FROM Department WHERE name=:name")
//            .setParameter("name", "Отдел производства").list().get(0);
//        session.delete(dept);




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
            StandardServiceRegistryBuilder.destroy(registry);
            throw e;
        }
    }


    private static LocalDate[] rand(){

        LocalDate date[] = new LocalDate[2];
        int vacation[] = new int[2];


        for(;;) {
            vacation[0] = (int) Math.round(Math.random() * 15 + 15); // random
            vacation[1]= (int) Math.round(Math.random() * 364 + 1);
            if ((vacation[0]+vacation[1])<365) break;
        }

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_YEAR, vacation[1]);
        date[0] = calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        calendar.add(Calendar.DAY_OF_YEAR, vacation[0]);
        date[1] = calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return date;
    }

}

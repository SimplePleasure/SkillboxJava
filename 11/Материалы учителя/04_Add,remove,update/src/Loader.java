import objects.Department;
import objects.Employee;
import objects.Vacation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.Math.random;

/**
 * Created by Danya on 26.10.2015.
 */
public class Loader {
    private static SessionFactory sessionFactory;


    public static LocalDate getDate() {
        String string = "January 2, 2010";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(string, formatter);
        return date;
    }

    public static void main(String[] args) {
        setUp();

        // create a couple of events...
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // проверяем не пуста ли таблица
        boolean tableIsEmpty = (Long) session.createQuery("SELECT COUNT(*) FROM Vacation").uniqueResult() == 0;

        // если пустая, заполняем
        if (tableIsEmpty) {
            createVacations(session);
        }

        List<Department> departments = (List<Department>) session.createQuery(
                "SELECT d FROM Department d"
        ).list();

        String queryPart1 = "SELECT\n" +
                "  e1.name,\n" +
                "  v1.begin_date,\n" +
                "  v1.end_date,\n" +
                "  e2.name AS name2,\n" +
                "  v2.begin_date AS begin_date2,\n" +
                "  v2.end_date AS end_date2,\n" +
                "  CASE WHEN v1.begin_date < v2.begin_date THEN v2.begin_date ELSE v1.begin_date END AS expr1,\n" +
                "  CASE WHEN v1.end_date < v2.end_date THEN v1.end_date ELSE v2.end_date END AS expr2,\n" +
                "  DATEDIFF(CASE WHEN v1.end_date < v2.end_date THEN v1.end_date ELSE v2.end_date END, \n" +
                "  CASE WHEN v1.begin_date < v2.begin_date THEN v2.begin_date ELSE v1.begin_date END) + 1 AS expr3\n" +
                "FROM vacation v1\n" +
                "  INNER JOIN vacation v2\n" +
                "    ON v1.employee_id <> v2.employee_id\n" +
                "  INNER JOIN employee e1\n" +
                "    ON v1.employee_id = e1.id\n" +
                "  INNER JOIN employee e2\n" +
                "    ON v2.employee_id = e2.id\n" +
                "    AND e2.department_id = e1.department_id\n" +
                "WHERE (v1.begin_date BETWEEN v2.begin_date AND v2.end_date\n" +
                "OR v1.end_date BETWEEN v2.begin_date AND v2.end_date)\n" +
                "AND e1.department_id = ";
        String queryPart2 = "\nORDER BY e1.name, v1.begin_date";

        System.out.println("\n\n");
        for (Department department : departments) {
            System.out.println(department.getName());
            String depID = department.getId().toString();

            List<Object[]> vacationOverlays = (List<Object[]>)
                    session.createSQLQuery(queryPart1 + depID + queryPart2).list();
            System.out.println(vacationOverlays.size() + " пересечений");

            for (Object[] col : vacationOverlays) {
                System.out.println(col[0] + " [" + col[1] + " : " + col[2]
                        + "] пересекается с " + col[3] + " [" + col[4] + " : "
                        + col[5] + "], на " + col[8] + " дней, с " + col[6] + " по " + col[7]);
            }
            System.out.println();
        }

        session.getTransaction().commit();
        session.close();

        //==================================================================
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    // распределяем отпуска
    private static void createVacations(Session session) {

        int yearNow = LocalDate.now().getYear();
        int dayBegin, monthBegin, weekDuration;
        LocalDate dateBegin;
        Random random = new Random();

        // получаем список сотрудников
        List<Employee> employees = (List<Employee>) session.createQuery(
                "SELECT e FROM Employee e"
        ).list();

        for (Employee employee : employees) {
            // заполняем на 2 года - текущий и следующий
            for (int year = yearNow; year <= yearNow + 1; year++) {
                // по 3 отпуска в год
                for (int i = 0; i < 3; i++) {
                    dayBegin = random.nextInt(28) + 1;      // генерируем число
                    monthBegin = random.nextInt(4) + 1 + 4 * i;    // генерируем месяц, разносим по третям года
                    weekDuration = random.nextInt(2) + 3;   // генерируем продолжительность отпуска в неделях

                    dateBegin = LocalDate.of(year, monthBegin, dayBegin);  // преобразуем в дату
                    Vacation vacation = new Vacation(employee, Date.valueOf(dateBegin),
                            Date.valueOf(dateBegin.plusWeeks(weekDuration)));  //создаём объект
                    session.save(vacation);     //сохраняем
                }
            }
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

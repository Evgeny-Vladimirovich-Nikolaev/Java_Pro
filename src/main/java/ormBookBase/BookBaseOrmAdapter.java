package ormBookBase;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ormBookBase.dto.AuthorDto;
import ormBookBase.dto.BookDto;

import java.util.Set;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class BookBaseOrmAdapter {

    private static final Configuration CONFIG;
    private final Set<BookDto> books;
    private final Set<AuthorDto> authors;

    static {
        CONFIG = new Configuration();
        CONFIG.configure();
    }

    public void restartTables(String table) {
        Session session = getSession();
        Transaction tran = session.beginTransaction() ;
        String hql = "from books";
        Query<BookDto> q = session.createQuery(hql, BookDto.class) ;
        q.executeUpdate() ;
        tran.commit() ;
    }

//    public void fillBaseFromVITR() {
//        runInsideSession(session -> {
//            Department department = new Department();
//            department.setId(12);
//            department.setName("Development department");
//            session.save(department);
//            System.out.println(department);
//
//            Employee employee = new Employee();
//            employee.setEmpName("Ivanov Vitalii");
//            employee.setDepartment(department);
//            employee.setSalary(new BigDecimal("1000000"));
//            session.save(employee);
//        });
//
//        runInsideSession(session -> {
//            final Department firstDepartment = session.find(Department.class, 188);
//            System.out.println(firstDepartment.getEmployees());
//
//
//            Query<Employee> searchQuery = session.createQuery("from Employee where empName like :name", Employee.class);
//            searchQuery.setParameter("name", "Iva%");
//            searchQuery.getResultList().forEach(System.out::println);
//
//        });
//    }

    public static void runInsideSession(Consumer<Session> consumer) {
        try (final Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            consumer.accept(session);
            transaction.commit();
        }
    }

    public static Session getSession() {
        // local SessionFactory bean created
        SessionFactory sessionFactory = CONFIG.buildSessionFactory();
        return sessionFactory.getCurrentSession();
    }
}

package ru.kolodin.model;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.kolodin.model.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class Service {

    private final List<Student> studentsCash;

    public Service() {
        studentsCash = new ArrayList<>();
    }

    private SessionFactory createSessionFactory() throws RuntimeException{
        Configuration configuration = new Configuration().configure();
        try {
            return configuration.buildSessionFactory();
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
    private Session createSession(SessionFactory sessionFactory) throws RuntimeException{
            try {
                return sessionFactory.openSession();
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
    }

    public String saveStudents() {
        try {
            SessionFactory sessionFactory = createSessionFactory();
            Session session = createSession(sessionFactory);
            Transaction transaction = session.beginTransaction();

            for (Student student: studentsCash) {
                session.persist(student);
            }

            transaction.commit();
            session.close();
            sessionFactory.close();
            studentsCash.clear();
            return "Данные временного списка успешно сохранены в базе данных";
        } catch (Exception e) {
            return "BeginTransaction: " + e.getMessage();
        }
    }

    public String loadStudents() {
        try {
            SessionFactory sessionFactory = createSessionFactory();
            Session session = createSession(sessionFactory);
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Student> criteria = criteriaBuilder.createQuery(Student.class);
            criteria.from(Student.class);
            studentsCash.clear();
            studentsCash.addAll(session.createQuery(criteria).getResultList());

            transaction.commit();
            session.close();
            sessionFactory.close();
            return "Данные успешно загружены из базы данных во временный список";
        } catch (Exception e) {
            return "BeginTransaction: " + e.getMessage();
        }
    }

    public String createNewStudent(String firstName, String secondName, String age) {
        Student student = new Student();
        student.setFirstname(firstName);
        student.setSecondname(secondName);
        student.setAge(Integer.parseInt(age));
        studentsCash.add(student);
        return "Студент успешно добавлен во временный список:\n" + student;
    }

    public String getStudentsCashString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nВременный список студентов:\n");
        for(Student student: studentsCash) {
            sb.append(student).append("\n");

        }
        return sb.toString();
    }

    public String findById(String id) {
        try {
            SessionFactory sessionFactory = createSessionFactory();
            Session session = createSession(sessionFactory);
            Transaction transaction = session.beginTransaction();

            Student student = session.find(Student.class, id);
            studentsCash.clear();
            studentsCash.add(student);

            transaction.commit();
            session.close();
            sessionFactory.close();
            if (studentsCash.isEmpty()) {
                return "Студент с id = " + id + " не найден.";
            } else {
                return "Студент с id = " + id + " найден и загружен во временный список.";
            }
        } catch (Exception e) {
            return "BeginTransaction: " + e.getMessage();
        }
    }

    public String editById(String id, String firstName, String secondName, String age) {
        try {
            findById(id);
        } catch (RuntimeException e) {
            return e.getMessage();
        }
        if (studentsCash.isEmpty()) {
            return "Студент с id = " + id + " не найден.";
        } else {
            try {
                SessionFactory sessionFactory = createSessionFactory();
                Session session = createSession(sessionFactory);
                Transaction transaction = session.beginTransaction();
                for (Student student: studentsCash) {
                    student.setFirstname(firstName);
                    student.setSecondname(secondName);
                    student.setAge(Integer.parseInt(age));
                    session.merge(student);
                }

                transaction.commit();
                session.close();
                sessionFactory.close();
                return "Данные временного списка успешно сохранены в базе данных";
            } catch (Exception e) {
                return "BeginTransaction: " + e.getMessage();
            }
        }
    }

    public String removeByIdFromTo(String idFrom, String idTo) {
        int idFromInt;
        int idToInt;
        if (Integer.parseInt(idFrom) < Integer.parseInt(idTo)) {
            idFromInt = Integer.parseInt(idFrom);
            idToInt = Integer.parseInt(idTo);
        } else {
            idFromInt = Integer.parseInt(idTo);
            idToInt = Integer.parseInt(idFrom);
        }

        try {
            SessionFactory sessionFactory = createSessionFactory();
            Session session = createSession(sessionFactory);
            Transaction transaction = session.beginTransaction();
            studentsCash.clear();
            for (int i = idFromInt; i <= idToInt ; i++) {
                Student student = session.find(Student.class, i);
                if (student != null) {
                    studentsCash.add(student);
                    session.remove(student);
                }
            }
            transaction.commit();
            session.close();
            sessionFactory.close();
            if (studentsCash.isEmpty()) {
                return "Студенты для удаления в базе данных не найдены";
            } else {
                return "Студенты загруженные во временный список удалены из базы данных.";
            }
        } catch (Exception e) {
            return "BeginTransaction: " + e.getMessage();
        }
    }

    public String findByQuery(String query) {
        try {
            SessionFactory sessionFactory = createSessionFactory();
            Session session = createSession(sessionFactory);
            Transaction transaction = session.beginTransaction();

            Query<Student> studentQuery = session.createQuery(query, Student.class);
            studentsCash.clear();
            studentsCash.addAll(studentQuery.getResultList());

            transaction.commit();
            session.close();
            sessionFactory.close();
            if (studentsCash.isEmpty()) {
                return "Результаты не найдены.";
            } else {
                return "Результаты поиска сохранены во временном списке.";
            }
        } catch (Exception e) {
            return "BeginTransaction: " + e.getMessage();
        }
    }
}

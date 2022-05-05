package hiberlab.runner;

import hiberlab.entity.Group;
import hiberlab.entity.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import hiberlab.utils.NewHibernateUtil;

public class Lab1 {

    public static void main(String[] args) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        List<Student> exStudents = session.createQuery("FROM Student S WHERE Status = :status_name").setParameter("status_name", "Отчислен").list();

        //Выборка
        //1.	Выбрать сведения об отчисленных студентах (ФИО студента, название группы).
        System.out.println("===Задание 1===\nОтчисленные студенты:");

        exStudents.forEach(System.out::println);

        //2.	Вывести данные студента, отчисленного последним.
        System.out.println("===Задание 2===\nПоследний отчисленный:");

        Student lastExp = exStudents.get(0);
        for (Student student : exStudents) {
            if (student.getStatusDate().after(lastExp.getStatusDate()))
                lastExp = student;
        }
        System.out.print(lastExp);

        session.flush();
        transaction.commit();
        session.close();
        sf.close();
    }
}

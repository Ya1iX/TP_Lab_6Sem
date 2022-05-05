package hiberlab.runner;

import hiberlab.entity.Group;
import hiberlab.entity.Student;
import hiberlab.utils.NewHibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class Lab2 {
    public static void main(String[] args) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        //Update
        //	Объединить группы одного года набора в одну группу, если общее количество студентов в них менее 20 студентов.
        System.out.println("\n2. Update\n===Задание 1===\nОбъединённые группы:");
        List<Group> groups = session.createQuery("FROM Group").list();

        HashSet<Integer> years = new HashSet<>();
        HashSet<String> specs = new HashSet<>();

        groups.forEach(g -> {
            years.add(g.getCreateDate().getYear());
            specs.add(g.getName().split("-")[0]);
        });

        List<Group> tempGroups = new ArrayList<>();
        List<Student> tempStudents = new ArrayList<>();
        for (int year : years) {
            for (String spec : specs) {
                for (Group group : groups) {
                    if (group.getCreateDate().getYear() == year && group.getName().split("-")[0].equals(spec)) {
                        tempGroups.add(group);
                    }
                }

                if (tempGroups.size() <= 1) {
                    tempGroups.clear();
                    tempStudents.clear();
                    continue;
                }

                int sumSize = 0;
                for (Group group : tempGroups) {
                    sumSize += group.getStudents().size();
                }

                if (sumSize < 20) {
                    tempGroups.forEach(g -> tempStudents.addAll(g.getStudents()));
                    tempGroups.get(0).setStudents(tempStudents);
                    tempGroups.get(0).setStatusDate(new Date());

                    tempStudents.forEach(s -> {
                        s.setGroup(tempGroups.get(0));
                        s.setStatusDate(new Date());
                        session.update(s);
                    });

                    tempGroups.forEach(g -> {
                        if (!g.equals(tempGroups.get(0))) {
                            g.setStatus("Расформирована");
                            g.setStatusDate(new Date());
                            g.setStudents(null);
                        }
                        session.update(g);
                    });
                }

                System.out.println(sumSize >= 20 ? "\nКоличство студентов в группах " + spec + " " + (year + 1900) + " года превышает, либо равно 20."
                        : "\nГруппа: " + tempGroups.get(0).getName()
                        + "\nСформирована из групп: " + tempGroups
                        + "\nДата формирования: " + tempGroups.get(0).getCreateDate() + "\nСтуденты: " + tempGroups.get(0).getStudents());

                tempGroups.clear();
                tempStudents.clear();
            }
        }

        session.flush();
        transaction.commit();
        session.close();
        sf.close();
    }
}

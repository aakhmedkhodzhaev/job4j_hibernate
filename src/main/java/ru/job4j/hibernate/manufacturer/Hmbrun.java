package ru.job4j.hibernate.manufacturer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;


public class Hmbrun {

    public static void main(String[] args) {
        List<Marks> list = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Marks bmw = Marks.of("BMW");
            Marks mercedes = Marks.of("Mercedes-Benz");
            session.persist(bmw);
            session.persist(mercedes);

            Models one = Models.of("X5", bmw);
            Models two = Models.of("X6", bmw);
            Models three = Models.of("Gelandewagen", mercedes);
            Models four = Models.of("W223", mercedes);
            Models five = Models.of("Maybach", mercedes);

            session.persist(one);
            session.persist(two);
            session.persist(three);
            session.persist(four);
            session.persist(five);
            session.getTransaction().commit();
            session.close();

            Session sessions = sf.openSession();
            sessions.beginTransaction();
            list = sessions.createQuery(
                    "select distinct c from Marks c join fetch c.models"
            ).list();
            sessions.getTransaction().commit();
            sessions.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (Marks marks : list) {
            for (Models models : marks.getModels()) {
                System.out.println(models);
            }
        }
    }
}
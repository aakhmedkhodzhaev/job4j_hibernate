package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.hibernate.model.Brand;
import ru.job4j.hibernate.model.Model;

public class HbmRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Brand one = Brand.of("Malibu");
            Brand two = Brand.of("Tracker");
            Brand three = Brand.of("Cruz");
            Brand four = Brand.of("Cavalier");
            Brand five = Brand.of("Camaro");
            // session.save(one);

            Model mx = Model.of("Chevrolet");
            mx.getBrands().add(one);
            mx.getBrands().add(two);
            mx.getBrands().add(three);
            mx.getBrands().add(four);
            mx.getBrands().add(five);
         // mx.addBrand(session.load(Brand.class, 1));

            session.save(mx);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

    }

}
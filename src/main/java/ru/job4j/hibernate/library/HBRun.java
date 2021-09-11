package ru.job4j.hibernate.library;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HBRun {
    public static void main(String [] args){
       final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
               .configure().build();
       try {
           SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
           Session session = sf.openSession();
           session.beginTransaction();

           Authors first = Authors.of("Булгаков M.A.");
           Authors second = Authors.of("Шоу Ирвин");

           Books aa = Books.of("Записки юного врача");
           Books bb = Books.of("Молодые львы");
           Books cc = Books.of("Машина времени");


           first.getBooks().add(aa);
           second.getBooks().add(bb);

           first.getBooks().add(cc);
           second.getBooks().add(cc);

           session.persist(first);
           session.persist(second);

           Authors authors = session.get(Authors.class, 1);
           session.remove(authors);

           session.getTransaction().commit();
           session.close();
       } catch (Exception e){
           e.printStackTrace();
       } finally {
         StandardServiceRegistryBuilder.destroy(registry);
       }
    }
}
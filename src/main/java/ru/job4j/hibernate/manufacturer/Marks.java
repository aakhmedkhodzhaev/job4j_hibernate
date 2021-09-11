package ru.job4j.hibernate.manufacturer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "s_mark")
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "marks")
    private List<Models> models = new ArrayList<>();

    public static Marks of(String name) {
        Marks marks = new Marks();
        marks.name = name;
        return marks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Models> getModels() {
        return models;
    }

    public void setModels(List<Models> models) {
        this.models = models;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marks marks = (Marks) o;
        return id == marks.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Marks {" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
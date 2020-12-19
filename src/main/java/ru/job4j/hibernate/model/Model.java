package ru.job4j.hibernate.model;

/**
 * @author Akhmedkhodzhaev A.A.
 * @version 1.0 19.11.2020
 * @task 1. ToMany[301848#329662]
 * @aim Написать код сохранения сущностей в БД
 * @others HbmRun запускает и добавляет данные по модели
 */

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "j_model")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Brand> brands = new ArrayList<>();

    public static Model of(String name) {
        Model model = new Model();
        model.name = name;
        return model;
    }

    public void addBrand(Brand b) {
        this.brands.add(b);
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

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Model)) return false;
        Model model = (Model) o;
        return getId() == model.getId();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }

}
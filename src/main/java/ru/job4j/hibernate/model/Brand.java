package ru.job4j.hibernate.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "m_brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public static Brand of(String name) {
        Brand brand = new Brand();
        brand.name = name;
        return brand;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof Brand)) {
            return false;
        }

        Brand brand = (Brand) o;
        return getId() == brand.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
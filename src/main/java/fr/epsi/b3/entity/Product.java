package fr.epsi.b3.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Product {
    public enum Type {
        FOOD,
        ACCESSORY,
        CLEANING
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String label;
    @Enumerated(EnumType.STRING)
    private Type type;
    private Double price;

    @ManyToMany(mappedBy = "products")
    private List<PetStore> petStores;

    public void setCode(String code) {
        this.code = code;
    }

    public List<PetStore> getPetStores() {
        return petStores;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", label='" + label + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", petStores=" + petStores +
                '}';
    }
}

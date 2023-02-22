package fr.epsi.b3.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class PetStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String managerName;
    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "petStore")
    private List<Animal> animals;

    @ManyToMany
    private List<Product> products;

    public void setName(String name) {
        this.name = name;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "PetStore{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", managerName='" + managerName + '\'' +
                ", address=" + address +
                ", animals=" + animals +
                ", products=" + products +
                '}';
    }
}

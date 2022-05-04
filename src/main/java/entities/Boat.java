package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Boat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    private String make;
    private String name;
    private String image;

    public Boat( String brand, String make, String name, String image, Owner owner, Harbour harbour) {
        this.id = id;
        this.brand = brand;
        this.make = make;
        this.name = name;
        this.image = image;
        this.owner = owner;
        this.harbour = harbour;
    }

    public Boat() {
    }

    @ManyToOne
    private Owner owner;

    @ManyToOne
    private Harbour harbour;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
        if (!owner.getBoats().contains(this)){
            owner.getBoats().add(this);
        }
    }

    public Harbour getHarbour() {
        return harbour;
    }

    public void setHarbour(Harbour harbour) {
        this.harbour = harbour;
        if (!harbour.getBoats().contains(this)){
            harbour.getBoats().add(this);
        }
    }

    @Override
    public String toString() {
        return "Boat{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", make='" + make + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", owner=" + owner +
                ", harbour=" + harbour +
                '}';
    }
}

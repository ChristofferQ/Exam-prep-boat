package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Owner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private int phone;

    public Owner() {
    }

    public Owner(String name, String address, int phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    @OneToMany(mappedBy = "owner")
    private List<Boat> boats = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public List<Boat> getBoats() {
        return boats;
    }

    public void setBoats(List<Boat> boats) {
        this.boats = boats;
    }

    public void addBoat(Boat boat){
        this.boats.add(boat);
        if(boat.getOwner() != this){
            boat.setOwner(this);
        }
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", boats=" + boats +
                '}';
    }
}

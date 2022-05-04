package dtos;

import entities.Owner;

import java.util.ArrayList;
import java.util.List;

public class OwnerDTO {
    private long id;
    private String name;
    private String address;
    private int phone;

    public OwnerDTO(long id, String name, String address, int phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public OwnerDTO(Owner o) {
        if(o.getId() != null)
            this.id = o.getId();
        this.name = o.getName();
        this.address = o.getAddress();
        this.phone = o.getPhone();
    }

    public static List<OwnerDTO> getDtos(List<Owner> os){
        List<OwnerDTO> odtos = new ArrayList<>();
        os.forEach(o->odtos.add(new OwnerDTO(o)));
        return odtos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Override
    public String toString() {
        return "OwnerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                '}';
    }
}

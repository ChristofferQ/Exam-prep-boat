package dtos;

import entities.Harbour;

import java.util.ArrayList;
import java.util.List;

public class HarbourDTO {

    private long harbourId;
    private String name;
    private String address;
    private int capacity;

    public HarbourDTO(String name, String address, int capacity) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }

    public HarbourDTO(Harbour h) {
        //if (h.getId() != null)
        this.harbourId = h.getId();
        this.name = h.getName();
        this.address = h.getAddress();
        this.capacity = h.getCapacity();
    }

    public static List<HarbourDTO> getDtos(List<Harbour> hs) {
        List<HarbourDTO> hdtos = new ArrayList<>();
        hs.forEach(h -> hdtos.add(new HarbourDTO(h)));
        return hdtos;
    }

    public long getHarbourId() {
        return harbourId;
    }

    public void setHarbourId(long harbourId) {
        this.harbourId = harbourId;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public long getId() {
        return harbourId;
    }

    public void setId(long harbourId) {
        this.harbourId = harbourId;
    }
}

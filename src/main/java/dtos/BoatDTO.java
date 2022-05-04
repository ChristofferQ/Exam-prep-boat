package dtos;

import entities.Boat;

import java.util.ArrayList;
import java.util.List;

public class BoatDTO {
    private long id;
    private String brand;
    private String make;
    private String name;
    private String image;

    public BoatDTO(long id, String brand, String make, String name, String image) {
        this.id = id;
        this.brand = brand;
        this.make = make;
        this.name = name;
        this.image = image;
    }

    public BoatDTO(Boat b){
//        if(b.getId() != null)
            this.id = b.getId();
        this.brand = b.getBrand();
        this.make = b.getMake();
        this.name = b.getName();
        this.image = b.getImage();
    }

    public static List<BoatDTO> getDtos(List<Boat> bs){
        List<BoatDTO> bdtos = new ArrayList<>();
        bs.forEach(b -> bdtos.add(new BoatDTO(b)));
        return bdtos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}

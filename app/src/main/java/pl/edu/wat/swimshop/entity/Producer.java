package pl.edu.wat.swimshop.entity;

public class Producer {
    private String id;
    private String brand;
    private String country;

    public Producer() {

    }

    public Producer(String id, String brand, String country) {
        this.id = id;
        this.brand = brand;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getCountry() {
        return country;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

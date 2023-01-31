package pl.edu.wat.swimshop.entity;

public class Products {

    private String id;
    private String name;
    private Double price;
    private String producer;

    public Products(String id, String name, Double price, String producer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.producer = producer;
    }

    public Products() {

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getProducer() {
        return producer;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}

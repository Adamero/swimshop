package pl.edu.wat.swimshop.dto;

public class ProductsRequest {
    private String name;
    private Double price;
    private String producer;

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getProducer() {
        return producer;
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

    public ProductsRequest(String name, Double price, String producer) {
        this.name = name;
        this.price = price;
        this.producer = producer;
    }
}

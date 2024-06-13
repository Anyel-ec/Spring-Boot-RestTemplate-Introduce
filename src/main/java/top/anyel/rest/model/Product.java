package top.anyel.rest.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Product {
    public Product(String name, String brand, Double price, String sku) {
        super();
        id = UUID.randomUUID().toString();
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.sku = sku;
    }


    private String id;
    private String name;
    private String brand;
    private Double price;
    private String sku;

}

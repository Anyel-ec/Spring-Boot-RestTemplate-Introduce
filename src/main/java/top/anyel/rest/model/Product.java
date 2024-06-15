package top.anyel.rest.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Product {
    public Product(String name, String brand, Double price, String sku,
                   String description, int amount, LocalDate releaseDate) {
        super();
        id = UUID.randomUUID().toString();
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.sku = sku;
        this.description = description;
        this.amount = amount;
        this.releaseDate = releaseDate;
        this.totalPrice = price * amount;
    }

    private String id;
    private String name;
    private String brand;
    private Double price;
    private String sku;
    private String description;
    private int amount;
    private LocalDate releaseDate;
    private Double totalPrice;
}

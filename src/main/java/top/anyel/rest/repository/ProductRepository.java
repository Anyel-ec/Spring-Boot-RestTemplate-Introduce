package top.anyel.rest.repository;

import org.springframework.stereotype.Repository;
import top.anyel.rest.model.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private List<Product> products = new ArrayList<>();
    public ProductRepository() {
        products.add(new Product("iPhone 12", "Apple", 799.99, "IPH12",
                "The latest iPhone", 100, LocalDate.of(2020, 10, 23)));
        products.add(new Product("Galaxy S21", "Samsung", 899.99, "GALS21",
                "The latest Galaxy", 100, LocalDate.of(2021, 1, 29)));
        products.add(new Product("Pixel 5", "Google", 699.99, "PIX5",
                "The latest Pixel", 100, LocalDate.of(2020, 10, 15)));
    }
    public List<Product> findAll() {
        return products;
    }
    public Optional<Product> findById(String id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
    public void save(Product product) {
        products.add(product);
    }
    public void update(Product product) {
        products.replaceAll(p -> p.getId().equals(product.getId()) ? product : p);
    }
    public void deleteById(String id) {
        products.removeIf(p -> p.getId().equals(id));
    }

}

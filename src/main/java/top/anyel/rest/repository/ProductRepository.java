package top.anyel.rest.repository;

import org.springframework.stereotype.Repository;
import top.anyel.rest.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private List<Product> products = new ArrayList<>();

    public ProductRepository() {
        products.add(new Product("Product 1", "Brand 1", 100.0, "SKU-1"));
        products.add(new Product("Product 2", "Brand 2", 200.0, "SKU-2"));
        products.add(new Product("Product 3", "Brand 3", 300.0, "SKU-3"));
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

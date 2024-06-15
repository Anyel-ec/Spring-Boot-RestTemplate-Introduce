package top.anyel.rest.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import top.anyel.rest.model.Product;
import top.anyel.rest.repository.RestTemplateRepository;

import java.util.List;
@Service
public class RestTemplateService {
    @Autowired
    private RestTemplateRepository restTemplateRepository;

    public String getProductAsJson() {
        return restTemplateRepository.getProductAsJson();
    }
    public List<Product> getProducts() {
        return restTemplateRepository.getProducts();
    }
    public List<?> getProductObjects() {
        return restTemplateRepository.getProductObjects();
    }
    public String createProduct(Product product) {
        return restTemplateRepository.createProduct(product);
    }
    public ResponseEntity<String> createProductWithExchange(Product product) {
        return restTemplateRepository.createProductWithExchange(product);
    }
    public void updateProductWithExchange(Product product) {
        restTemplateRepository.updateProductWithExchange(product);
    }
    public void getProductAsStream(Product product) {
        restTemplateRepository.getProductAsStream(product);
    }
}

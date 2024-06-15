package top.anyel.rest.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.anyel.rest.model.Product;
import top.anyel.rest.service.RestTemplateService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class RestTemplateController {
    @Autowired
    private RestTemplateService restTemplateService;
    @GetMapping("/json")
    public String getProductAsJson() {
        return restTemplateService.getProductAsJson();
    }
    @GetMapping
    public List<Product> getProducts() {
        return restTemplateService.getProducts();
    }
    @GetMapping("/objects")
    public List<?> getProductObjects() {
        return restTemplateService.getProductObjects();
    }
    @PostMapping
    public String createProduct(@RequestBody Product product) {
        return restTemplateService.createProduct(product);
    }
    @PostMapping("/exchange")
    public ResponseEntity<String> createProductWithExchange(@RequestBody Product product) {
        return restTemplateService.createProductWithExchange(product);
    }
    @PutMapping
    public void updateProductWithExchange(@RequestBody Product product) {
        restTemplateService.updateProductWithExchange(product);
    }

    @GetMapping("/stream")
    public void getProductAsStream(@RequestBody Product product) {
        restTemplateService.getProductAsStream(product);
    }


}

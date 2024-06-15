package top.anyel.rest.repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import top.anyel.rest.model.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Repository
public class RestTemplateRepository {
    private static final String resourceUrl = "http://localhost:8080/products";
    private final RestTemplate restTemplate = new RestTemplate();

    public String getProductAsJson() {
        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
        return response.getBody();
    }

    public List<Product> getProducts() {
        ResponseEntity<List> response = restTemplate.getForEntity(resourceUrl, List.class);
        return response.getBody();
    }

    public List<?> getProductObjects() {
        return restTemplate.getForObject(resourceUrl, List.class);
    }

    public String createProduct(Product product) {
        HttpEntity<Product> request = new HttpEntity<>(product);
        return restTemplate.postForObject(resourceUrl, request, String.class);
    }

    public ResponseEntity<String> createProductWithExchange(Product product) {
        HttpEntity<Product> request = new HttpEntity<>(product);
        return restTemplate.exchange(resourceUrl, HttpMethod.POST, request, String.class);
    }
    public void updateProductWithExchange(Product product) {
        HttpEntity<Product> request = new HttpEntity<>(product);
        restTemplate.exchange(resourceUrl, HttpMethod.PUT, request, Void.class);
    }
    public void getProductAsStream(Product product) {
        RequestCallback requestCallback = request -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(request.getBody(), product);
            request.getHeaders().setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM, MediaType.ALL));
        };
        ResponseExtractor<Void> responseExtractor = response -> {
            Path path = Paths.get("some/path");
            Files.copy(response.getBody(), path);
            return null;
        };
        restTemplate.execute(resourceUrl, HttpMethod.GET, requestCallback, responseExtractor);
    }
}

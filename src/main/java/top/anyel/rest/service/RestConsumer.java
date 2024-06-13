package top.anyel.rest.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import top.anyel.rest.model.Product;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
public class RestConsumer {

    static final String resourceUrl = "http://localhost:8080/products";
    public void getProductAsJson() {
        RestTemplate restTemplate = new RestTemplate();

        // Fetch JSON response as String wrapped in ResponseEntity
        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);

        String productsJson = response.getBody();
        System.out.println(productsJson);
    }
    public void getProducts() {
        RestTemplate restTemplate = new RestTemplate();
        // Fetch response as List wrapped in ResponseEntity
        ResponseEntity<List> response
                = restTemplate.getForEntity(resourceUrl, List.class);

        List<Product> products = response.getBody();
        System.out.println(products);
    }

    public void getProductObjects() {

        RestTemplate restTemplate = new RestTemplate();

        // Fetching response as Object
        List<?> products
                = restTemplate.getForObject(resourceUrl, List.class);

        System.out.println(products);
    }


    public void createProduct() {
        RestTemplate restTemplate = new RestTemplate();
        // Create the request body by wrapping
        // the object in HttpEntity
        HttpEntity<Product> request = new HttpEntity<Product>(
                new Product("Television", "Samsung",1145.67,"S001"));
        // Send the request body in HttpEntity for HTTP POST request
        String productCreateResponse = restTemplate
                .postForObject(resourceUrl, request, String.class);

        System.out.println(productCreateResponse);
    }

    public void createProductWithExchange() {
        RestTemplate restTemplate = new RestTemplate();
        // Create the request body by wrapping
        // the object in HttpEntity
        HttpEntity<Product> request =
                new HttpEntity<Product>(
                        new Product("Television", "Samsung",1145.67,"S001"));
        ResponseEntity<String> productCreateResponse =
                restTemplate
                        .exchange(resourceUrl, org.springframework.http.HttpMethod.POST, request, String.class);

        System.out.println(productCreateResponse);
    }

    public void updateProductWithExchange() {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl
                = "http://localhost:8080/products";
        // Create the request body by wrapping
        // the object in HttpEntity
        HttpEntity<Product> request = new HttpEntity<Product>(
                new Product("Television", "Samsung",1145.67,"S001"));
        // Send the PUT method as a method parameter
        restTemplate.exchange(
                resourceUrl,
                HttpMethod.PUT,
                request,
                Void.class);


    }

    public void getProductasStream() {
        final Product fetchProductRequest =
                new Product("Television", "Samsung",1145.67,"S001");
        RestTemplate restTemplate = new RestTemplate();

        // Set HTTP headers in the request callback
        RequestCallback requestCallback = request -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(request.getBody(),
                    fetchProductRequest);
            request.getHeaders()
                    .setAccept(Arrays.asList(
                            MediaType.APPLICATION_OCTET_STREAM,
                            MediaType.ALL));
        };
        // Processing the response. Here we are extracting the
        // response and copying the file to a folder in the server.
        ResponseExtractor<Void> responseExtractor = response -> {
            Path path = Paths.get("some/path");
            Files.copy(response.getBody(), path);
            return null;
        };
        restTemplate.execute(resourceUrl,
                HttpMethod.GET,
                requestCallback,
                responseExtractor );

    }

    public static void main(String[] args) {
        RestConsumer consumer = new RestConsumer();
        consumer.getProductAsJson();
        consumer.getProducts();
        consumer.getProductObjects();
        consumer.createProduct();
        consumer.createProductWithExchange();
        consumer.updateProductWithExchange();
        consumer.getProductasStream();
    }

}

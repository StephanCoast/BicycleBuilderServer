package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.model.Product;
import shop.repository.ProductRepository;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Optional;

@RestController
@RequestMapping("/products/{id}")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping(produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getProductImage(@PathVariable("id") Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            byte[] image = product.get().getImage();
            InputStream is = new BufferedInputStream(new ByteArrayInputStream(image));
            String mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
            try {
                mimeType = URLConnection.guessContentTypeFromStream(is);
            } catch (IOException e) { }
            return ResponseEntity.ok().header("Content-Type", mimeType).body(image);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getProduct(@PathVariable("id") Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }
}

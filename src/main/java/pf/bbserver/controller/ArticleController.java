package pf.bbserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pf.bbserver.model.Article;
import pf.bbserver.repository.ArticleRepo;

import java.util.Optional;

@RestController
@RequestMapping("/articles/{id}")
public class ArticleController {

    @Autowired
    ArticleRepo articleRepo;

//    @GetMapping(produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
//    public ResponseEntity<byte[]> getProductImage(@PathVariable("id") Integer id) {
//        Optional<Artikel> artikel = artikelRepo.findById(id);
//        if (artikel.isPresent()) {
//            byte[] image = artikel.get().getImage();
//            InputStream is = new BufferedInputStream(new ByteArrayInputStream(image));
//            String mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
//            try {
//                mimeType = URLConnection.guessContentTypeFromStream(is);
//            } catch (IOException e) { }
//            return ResponseEntity.ok().header("Content-Type", mimeType).body(image);
//        }
//        return ResponseEntity.notFound().build();
//    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getProduct(@PathVariable("id") Integer id) {
        Optional<Article> product = articleRepo.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }
}

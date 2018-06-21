package microservices.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ProductResource {

    private static Logger logger = LogManager.getLogger(ProductResource.class);

    @Autowired
    private ProductReposity productReposity;

    @GetMapping("/products")
    public List<Product> getAll() {
        return productReposity.findAll();
    }

    @GetMapping("/products/{id}/cid/{cid}/caller/{caller}")
    public Product getOne(@PathVariable int id, @PathVariable int cid, @PathVariable String caller) {
        logger.info("CALL\t{}\t{}\tProducts\t/products/{}", cid, caller, id);
        Optional<Product> one = productReposity.findById(id);
        return one.get();
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable int id) {
        productReposity.deleteById(id);
    }

    @PostMapping("/products")
    public void create(@RequestBody Product product) {
        Product savedProduct = productReposity.save(product);
    }

    @PutMapping("/products/{id}")
    public void update(@RequestBody Product product, @PathVariable int id) {
        Optional<Product> a = productReposity.findById(id);

        product.setId(id);
        productReposity.save(product);
    }
}

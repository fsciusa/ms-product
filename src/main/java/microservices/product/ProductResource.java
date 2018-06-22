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
@RequestMapping("/product")

public class ProductResource {

    private static Logger logger = LogManager.getLogger(ProductResource.class);

    @Autowired
    private ProductReposity productReposity;

    @GetMapping("/all")
    public List<Product> getAll() {
        return productReposity.findAll();
    }

    @GetMapping("/all/cid/{cid}/caller/{caller}")
    public List<Product> getAll(@PathVariable int cid, @PathVariable String caller) {
        logger.info("RES\t{}\t{}\tproduct\t/all/", cid, caller);
        return productReposity.findAll();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable int id) {
        Optional<Product> one = productReposity.findById(id);
        return one.get();
    }

    @GetMapping("/{id}/cid/{cid}/caller/{caller}")
    public Product getOne(@PathVariable int id, @PathVariable int cid, @PathVariable String caller) {
        logger.info("RES\t{}\t{}\tproduct\t/{}", cid, caller, id);
        Optional<Product> one = productReposity.findById(id);
        return one.get();
    }

    @GetMapping("/price/{id}/cid/{cid}/caller/{caller}")
    public double getPrice(@PathVariable int id, @PathVariable int cid, @PathVariable String caller) {
        logger.info("RES\t{}\t{}\tproduct\t/price/{}", cid, caller, id);
        Optional<Product> one = productReposity.findById(id);
        return one.get().getPrice();
    }

    @PostMapping("/create")
    public void create(@RequestBody Product product) {
        Product savedProduct = productReposity.save(product);
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody Product product, @PathVariable int id) {
        product.setId(id);
        productReposity.save(product);
    }

    @PutMapping("/update/{id}/price/{price}")
    public void update(@PathVariable int id, @PathVariable double price) {
        Optional<Product> one = productReposity.findById(id);
        one.get().setPrice(price);
        productReposity.save(one.get());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        productReposity.deleteById(id);
    }

}

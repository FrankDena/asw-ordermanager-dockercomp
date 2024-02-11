package asw.ordermanager.ordervalidationservice.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private final java.util.logging.Logger logger = Logger.getLogger(this.getClass().toString());


    public Product createProduct(String name, int stockLevel) {
        logger.info("CREATE PRODUCT BY ORDER VALIDATION SERVICE");
        Product product = new Product(name, stockLevel);
        product = productRepository.save(product);
        return product;
    }

    public Product getProduct(String name) {
        Product product = productRepository.findById(name).orElse(null);
        return product;
    }

    public List<Product> getProductsByNames(List<String> names) {
        List<Product> products = (List<Product>) productRepository.findByNameIn(names);
        return products;
    }

    public Product updateProductStockLevel(String name, int stockLevelVariation) {
        logger.info("UPDATE STOCK LEVEL BY ORDER VALIDATION SERVICE");
        Product product = getProduct(name);
        product.setStockLevel(product.getStockLevel() + stockLevelVariation);
        product = productRepository.save(product);
        return product;
    }
}

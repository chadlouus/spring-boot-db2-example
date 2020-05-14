package guru.springframework.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Product;

@Service
public class StressServiceImpl implements StressService {

    private ProductService productService;

    @Autowired
    public StressServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void stressInsert(int total) {
        for (int i = 0; total > 0 && i < total; i++) {
            Long id = null;
            int randomInt = (int)(Math.random()*100);
            String description = "des " + randomInt;
            BigDecimal price = new BigDecimal(randomInt);
            String imageUrl = "imageUrl-" + randomInt;
            Product product = new Product(id, description, price, imageUrl);
            productService.saveOrUpdate(product);
        }
    }

    @Override
	public void stressDelete(int total) {
        List<Product> productList = productService.listAll();
        for (int i = 0; total > 0 && i < total && i < productList.size(); i++) {
            Product product = (Product) productList.get(i);
            productService.delete(Long.valueOf(product.getId()));
        }
    }

    @Override
    public void stressRandomGet(int total) {
        for (int i = 0; i < total; i++) {
            long randomLong = (long)(Math.random()*1000);
            Product product = productService.getById(Long.valueOf(randomLong));
            System.out.println("found product id " + randomLong + " : " + ((product == null)? false: true));
        }
    }

    @Override
    public void stressRandomDelete(int total) {
        for (int i = 0; i < total; i++) {
            long randomLong = (long)(Math.random()*1000);
            try {
                productService.delete(Long.valueOf(randomLong));
            }
            catch (Exception ex) {
                System.out.println("cannot delete product id: " + randomLong);
                // ignore
            }
        }
    }
}




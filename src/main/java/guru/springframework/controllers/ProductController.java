package guru.springframework.controllers;

import guru.springframework.commands.ProductForm;
import guru.springframework.converters.ProductToProductForm;
import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

/**
 * Created by jt on 1/10/17.
 */
@Controller
public class ProductController {
    private ProductService productService;

    private ProductToProductForm productToProductForm;

    @Autowired
    public void setProductToProductForm(ProductToProductForm productToProductForm) {
        this.productToProductForm = productToProductForm;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/")
    public String redirToList(){
        return "redirect:/product/list";
    }

    @RequestMapping({"/product/list", "/product"})
    public String listProducts(Model model){
        model.addAttribute("products", productService.listAll());
        return "product/list";
    }

    @RequestMapping("/product/show/{id}")
    public String getProduct(@PathVariable String id, Model model){
        model.addAttribute("product", productService.getById(Long.valueOf(id)));
        return "product/show";
    }

    @RequestMapping("/product/stress/insert/{total}")
    public String stressInsert(@PathVariable String total, Model model) {
        int totalNumber = Integer.parseInt(total);
        for (int i = 0; totalNumber > 0 && i < totalNumber; i++) {
            Long id = null;
            int randomInt = (int)(Math.random()*100);
            String description = "des " + randomInt;
            BigDecimal price = new BigDecimal(randomInt);
            String imageUrl = "imageUrl-" + randomInt;
            Product product = new Product(id, description, price, imageUrl);
            productService.saveOrUpdate(product);
        }
        return "redirect:/product/list";
    }

    @RequestMapping("/product/stress/delete/{total}")
    public String stressDelete(@PathVariable String total, Model model) {
        int totalNumber = Integer.parseInt(total);
        List<Product> productList = productService.listAll();
        for (int i = 0; totalNumber > 0 && i < totalNumber; i++) {
            Product product = (Product) productList.get(i);
            productService.delete(Long.valueOf(product.getId()));
        }
        return "redirect:/product/list";
    }

    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Product product = productService.getById(Long.valueOf(id));
        ProductForm productForm = productToProductForm.convert(product);

        model.addAttribute("productForm", productForm);
        return "product/productform";
    }

    @RequestMapping("/product/new")
    public String newProduct(Model model){
        model.addAttribute("productForm", new ProductForm());
        return "product/productform";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveOrUpdateProduct(@Valid ProductForm productForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "product/productform";
        }

        Product savedProduct = productService.saveOrUpdateProductForm(productForm);

        return "redirect:/product/show/" + savedProduct.getId();
    }

    @RequestMapping("/product/delete/{id}")
    public String delete(@PathVariable String id){
        productService.delete(Long.valueOf(id));
        return "redirect:/product/list";
    }
}

package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.math.BigDecimal;

/**
 * Created by jt on 1/10/17.
 */
@Entity
public class Product {

    @javax.persistence.Id
    @GeneratedValue
    private Long id;
    private String description;
    private BigDecimal price;
    private String imageUrl;

    public Product() {
    }

    public Product(Long id, String description, BigDecimal price, String imageUrl) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

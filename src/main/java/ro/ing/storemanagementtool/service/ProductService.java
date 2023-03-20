package ro.ing.storemanagementtool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ro.ing.storemanagementtool.domain.Product;
import ro.ing.storemanagementtool.dto.ProductDto;
import ro.ing.storemanagementtool.exception.ProductCreationException;
import ro.ing.storemanagementtool.repository.ProductRepository;
import ro.ing.storemanagementtool.util.Utils;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public void addProduct(ProductDto productDto) {
        Product createdProduct = this.toEntity(productDto);
        createdProduct.setAppId(Utils.generateRandomUniqueId());
        if(createdProduct.getProductName().isEmpty() ||
                createdProduct.getPrice().isEmpty() ||
                createdProduct.getDescription().isEmpty()) {
                logger.error("ProductName/Price/Description is empty!");
                throw new ProductCreationException("All fields are mandatory");
            }
        try {
            this.toDto(productRepository.save(createdProduct));
        } catch (DataAccessException e) {
            logger.error("Cannot access database!");
        }
    }

    public void changePriceOfProduct(Long appId, String newPrice) {
        Product product = productRepository.findByAppId(appId);
        try {
            Long.parseLong(newPrice);
        }
        catch(Exception e) {
            logger.error("Price input is not a number or null!");
            return;
        }
        product.setPrice(newPrice);
        try {
            productRepository.save(product);
        } catch(DataAccessException e) {
            logger.error("Cannot access database!");
        }
    }

    public ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductName(product.getProductName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setAppId(product.getAppId());
        return productDto;
    }

    public Product toEntity(ProductDto productDto) {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setAppId(productDto.getAppId());
        return product;
    }

    public List<ProductDto> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public ProductDto getProductByAppId(Long appId) {
        return this.toDto(productRepository.findByAppId(appId));
    }

    public List<ProductDto> searchProductByNameOrDescription(String key) {
        List<Product> productList = productRepository.findAllByProductNameContainingOrDescriptionContaining(key, key);
        return productList.stream().map(this::toDto).collect(Collectors.toList());
    }
}

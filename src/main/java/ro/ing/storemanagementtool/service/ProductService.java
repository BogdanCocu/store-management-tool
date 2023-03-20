package ro.ing.storemanagementtool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ing.storemanagementtool.domain.Product;
import ro.ing.storemanagementtool.dto.ProductDto;
import ro.ing.storemanagementtool.repository.ProductRepository;
import ro.ing.storemanagementtool.util.Utils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductDto addProduct(ProductDto productDto) {
        Product createdProduct = this.toEntity(productDto);
        createdProduct.setAppId(Utils.generateRandomUniqueId());
        return this.toDto(productRepository.save(createdProduct));
    }

    public void changePriceOfProduct(Long appId, String newPrice) {
        Product product = productRepository.findByAppId(appId);
        product.setPrice(newPrice);
        productRepository.save(product);
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

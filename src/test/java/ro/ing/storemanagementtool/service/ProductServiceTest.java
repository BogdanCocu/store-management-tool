package ro.ing.storemanagementtool.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ro.ing.storemanagementtool.domain.Product;
import ro.ing.storemanagementtool.dto.ProductDto;
import ro.ing.storemanagementtool.repository.ProductRepository;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
public class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private static final Long ID = 1L;
    private static final Long APPID = 10L;
    private static final String PRODUCT_NAME = "NAME";
    private static final String PRICE = "30";
    private static final String DESCRIPTION = "DESCRIPTION";

    @Test
    public void changePriceOfProduct() {
        Product product = initProduct();
        productService.changePriceOfProduct(APPID, "10");
        assertEquals("10", product.getPrice());
    }

    @Test
    public void addProduct() {
        Product product = initProduct();
        Product addedProduct = productService.addProduct(productService.toDto(product));
        assertEquals(addedProduct, product);
    }

    @Test
    public void getAllProducts() {
        Product product = initProduct();
        List<Product> products = Collections.singletonList(product);
        when(productRepository.findAll()).thenReturn(products);
        assertEquals(product, productService.toEntity(productService.getAllProducts().get(0)));
    }

    @Test
    public void convertProductEntityToDto() {
        Product product = initProduct();
        ProductDto productDto = productService.toDto(product);
        assertEquals(product.getProductName(), productDto.getProductName());
        assertEquals(product.getPrice(), productDto.getPrice());
        assertEquals(product.getDescription(), productDto.getDescription());
        assertEquals(product.getAppId(), productDto.getAppId());
    }

    @Test
    public void convertProductDtoToEntity() {
        ProductDto productDto = new ProductDto();
        productDto.setProductName(PRODUCT_NAME);
        productDto.setAppId(APPID);
        productDto.setPrice(PRICE);
        productDto.setDescription(DESCRIPTION);

        Product product = productService.toEntity(productDto);
        assertEquals(productDto.getProductName(), product.getProductName());
        assertEquals(productDto.getPrice(), product.getPrice());
        assertEquals(productDto.getDescription(), product.getDescription());
        assertEquals(productDto.getAppId(), product.getAppId());
    }

    @Test
    public void getProductByAppId() {
        Product product = initProduct();
        Product foundProduct = productService.toEntity(productService.getProductByAppId(APPID));
        assertEquals(product, foundProduct);
    }

    @Test
    public void searchProduct() {
        Product product1 = initProduct();
        Product product2 = new Product("Iphone", "9999", "Apple product");
        Product product3 = new Product("Samsung phone", "999", "Samsung product");

        List<Product> searchProductsResults = productRepository.findAllByProductNameContainingOrDescriptionContaining("phone", "phone");
        assertThat(searchProductsResults).containsOnly(product2, product3);
    }

        private Product initProduct() {
        Product product = new Product();
        product.setId(ID);
        product.setAppId(APPID);
        product.setProductName(PRODUCT_NAME);
        product.setPrice(PRICE);
        product.setDescription(DESCRIPTION);
        return product;
    }
}

package ro.ing.storemanagementtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ing.storemanagementtool.dto.ProductDto;
import ro.ing.storemanagementtool.service.ProductService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/addProduct")
    public void addProduct(@RequestBody ProductDto productDto) {
        this.productService.addProduct(productDto);
    }

    @PostMapping("/changePrice/{appId}")
    public void changePrice(@PathVariable Long appId, @RequestParam String newPrice) {
        this.productService.changePriceOfProduct(appId, newPrice);
    }

    @GetMapping("/getProducts")
    public List<ProductDto> getProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping("/getProductByAppId/{appId}")
    public ProductDto getProductByAppId(@PathVariable Long appId) {
        return this.productService.getProductByAppId(appId);
    }

    @GetMapping("/searchProducts/{key}")
    public List<ProductDto> searchProducts(@PathVariable String key) {
        return this.productService.searchProductByNameOrDescription(key);
    }
}

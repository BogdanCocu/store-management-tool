package ro.ing.storemanagementtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ing.storemanagementtool.dto.ProductDto;
import ro.ing.storemanagementtool.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/addProduct")
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return this.productService.addProduct(productDto);
    }

    @PostMapping("/changePrice/{appId}")
    public void changePrice(@PathVariable Long appId, @RequestParam String newPrice) {
        this.productService.changePriceOfProduct(appId, newPrice);
    }
}

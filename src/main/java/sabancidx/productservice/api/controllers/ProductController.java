package sabancidx.productservice.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sabancidx.productservice.business.abstracts.ProductService;
import sabancidx.productservice.core.utilities.DataResult;
import sabancidx.productservice.core.utilities.Result;
import sabancidx.productservice.dataAccess.StockDao;
import sabancidx.productservice.entities.concretes.Product;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;


    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAll")
    public DataResult<List<Product>> getAll() {
        return productService.getAll();
    }

    @GetMapping("/findAllByName")
    public DataResult<List<Product>> findAllByName(String productName){
        return productService.findAllByProductName(productName);
    }

    @GetMapping("/findById")
    public DataResult<Product> findById(@RequestParam("productId") Integer productId){
        return productService.findById(productId);
    }

    @GetMapping("/findByCode")
    public Result findByCode(String productCode){
        return productService.findByProductCode(productCode);
    }

    @GetMapping("/findAllByBrand")
    public Result findAllByBrand(String brand){
        return productService.findAllByBrand(brand);
    }

    @GetMapping("/findAllByPrice")
    public Result findAllByPrice(double minPrice,double maxPrice){
        return productService.findAllByPriceBetween(minPrice,maxPrice);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Product product){
        Result result = productService.add(product);
        if(result.isSuccess())
            return ResponseEntity.ok().body(result);
        else
            return ResponseEntity.badRequest().body(result);

    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateJobAdvertisementStatus(@PathVariable("id") Integer id, @RequestBody Boolean isActive) {
        Result result = productService.updateProductStatus(id, isActive);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/update")
    public DataResult<Product> update(@RequestBody Product product){
        return productService.updateProduct(product);
    }
}
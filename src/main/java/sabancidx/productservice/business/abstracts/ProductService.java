package sabancidx.productservice.business.abstracts;

import sabancidx.productservice.core.utilities.DataResult;
import sabancidx.productservice.core.utilities.Result;
import sabancidx.productservice.entities.concretes.Product;

import java.util.List;

public interface ProductService {

    DataResult<List<Product>> getAll();
    Result add(Product product);
    DataResult<List<Product>> findAllByProductName(String productName);
    DataResult<Product> findById(Integer id);
    Result findByProductCode(String productCode);
    DataResult<List<Product>> findAllByBrand(String brand);
    DataResult<List<Product>> findAllByPriceBetween(double minPrice,double maxPrice);
    Result updateProductStatus(Integer id, boolean isActive);
    DataResult<Product> updateProduct(Product product);
}

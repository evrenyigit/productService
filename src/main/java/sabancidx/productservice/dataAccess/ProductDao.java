package sabancidx.productservice.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sabancidx.productservice.entities.concretes.Product;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product,Integer> {

    List<Product> findAllByIsActiveTrue();
    List<Product> findAllByProductNameAndIsActiveTrue(String productName);
    Product findByProductCode(String productCode);
    Product findByIdAndIsActiveTrue(Integer id);
    List<Product> findAllByBrandAndIsActiveTrue(String brand);
    List<Product> findAllByPriceBetweenAndIsActiveTrue(double minPrice,double maxPrice);

    boolean existsById(Integer Id);

    Product findByProductCodeAndAndIsActiveTrue(String productCode);
}

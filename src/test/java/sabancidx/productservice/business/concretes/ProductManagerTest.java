package sabancidx.productservice.business.concretes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sabancidx.productservice.business.abstracts.ProductService;
import sabancidx.productservice.dataAccess.ProductDao;
import sabancidx.productservice.entities.concretes.Product;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@SpringBootTest
public class ProductManagerTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDao productDao;

    @DisplayName("Unit test for ProductController getAll function")
    @Test
    public void givenProductListGetAll(){

        Product product = Product.builder().
                id(20).productName("havlu").
                productCode("asdsadsa").
                price(100).stockInUnit(100).
                isActive(true).build();

    }


}

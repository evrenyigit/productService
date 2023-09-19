package sabancidx.productservice.business.concretes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sabancidx.productservice.business.abstracts.ProductService;
import sabancidx.productservice.core.utilities.*;
import sabancidx.productservice.dataAccess.ProductDao;
import sabancidx.productservice.dataAccess.StockDao;
import sabancidx.productservice.entities.concretes.Product;
import sabancidx.productservice.entities.concretes.Stock;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductManager implements ProductService {
    Logger log = LoggerFactory.getLogger(this.getClass());
    private ProductDao productDao;

    private StockDao stockDao;


    public ProductManager(ProductDao productDao, StockDao stockDao) {
        this.productDao = productDao;
        this.stockDao = stockDao;
    }


    @Override
    public DataResult<List<Product>> getAll() {
        return new SuccessDataResult<List<Product>>(productDao.findAllByIsActiveTrue());
    }

    @Override
    public Result add(Product product) {
        String productCode = UUID.randomUUID().toString();
        productCode = productCode.substring(productCode.length() - 10, productCode.length());
        product.setProductCode(productCode);
        product = productDao.save(product);
        return new SuccessResult("Ürün başarıyla eklendi.");
    }

    @Override
    public DataResult<List<Product>> findAllByProductName(String productName) {
        return new SuccessDataResult<List<Product>>("Ürünler listelendi.", productDao.findAllByProductNameAndIsActiveTrue(productName));
    }

    @Override
    public Result findByProductCode(String productCode) {
        return new SuccessResult("Ürün kodu" + productDao.findByProductCode(productCode) + "olan ürün listelendi.");
    }

    @Override
    public DataResult<List<Product>> findAllByBrand(String brand) {
        return new SuccessDataResult<List<Product>>("Aynı marka ürünler listelendi.", productDao.findAllByBrandAndIsActiveTrue(brand));
    }

    @Override
    public DataResult<List<Product>> findAllByPriceBetween(double minPrice, double maxPrice) {
        return new SuccessDataResult<List<Product>>("Belirtilen fiyatların arasındaki ürünler listelendi.",
                productDao.findAllByPriceBetweenAndIsActiveTrue(minPrice, maxPrice));
    }

    @Override
    public Result updateProductStatus(Integer id, boolean isActive) {
        Product product = productDao.getById(id);

        if (product == null)
            throw new EntityNotFoundException("Bu id ile bir product bulunmuyor.");

        product.setIsActive(isActive);
        productDao.save(product);
        return new SuccessResult("İş ilanının durumu değiştirildi.");
    }

    @Override
    public DataResult<Product> findById(Integer id) {

        Product product = productDao.findByIdAndIsActiveTrue(id);
        return new SuccessDataResult("Ürünler detay.", product);
    }

    @Override
    public DataResult<Product> updateProduct(Product product) {
        if (productDao.existsById(product.getId())) {
            productDao.save(product);
            return new SuccessDataResult<>("Başarı ile güncellendi.", product);
        } else {
            return new ErrorDataResult<>("Kayıt bulunamadı.", product);
        }

    }

    @Scheduled(fixedRate = 5000)
    @Transactional
    public void stockUpdate() {
        List<Stock> stockList = stockDao.findAll();
        stockList.forEach(stock -> {
            Product product = productDao.findByProductCodeAndAndIsActiveTrue(stock.getProductCode());
            product.setStockInUnit(stock.getStockInUnit());
            productDao.save(product);
            stockDao.delete(stock);
            log.info(product.getProductName() + " - " + product.getStockInUnit() + " - stok güncellendi");
        });

    }

}

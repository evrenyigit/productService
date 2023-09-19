package sabancidx.productservice.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sabancidx.productservice.entities.concretes.Stock;


@Repository
public interface StockDao extends JpaRepository<Stock,Integer> {


}

package sabancidx.productservice.entities.concretes;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="product_name")
    private String productName;

    @NaturalId
    @Column(name="product_code")
    private String productCode;

    @Column(name="instruction")
    private String instruction;

    @Column(name="brand")
    private String brand;

    @Column(name="price")
    private double price;

    @Column(name="stock_in_unit")
    private Integer stockInUnit;

    @Column(name= "status",nullable = false)
    private Boolean isActive;

}

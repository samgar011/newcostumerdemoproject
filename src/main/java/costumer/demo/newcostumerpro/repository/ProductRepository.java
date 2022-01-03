package costumer.demo.newcostumerpro.repository;

import costumer.demo.newcostumerpro.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepository extends JpaRepository <Product, Long> {
    List<Product> findAllByCategory_Id(int id);
}

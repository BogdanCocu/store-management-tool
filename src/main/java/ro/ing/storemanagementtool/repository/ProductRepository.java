package ro.ing.storemanagementtool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ing.storemanagementtool.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}

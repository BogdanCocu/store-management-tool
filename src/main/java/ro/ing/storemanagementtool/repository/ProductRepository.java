package ro.ing.storemanagementtool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ing.storemanagementtool.domain.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByAppId(Long appId);
    List<Product> findAllByProductNameContainingOrDescriptionContaining(String key1, String key2);
}

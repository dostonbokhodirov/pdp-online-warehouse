package uz.doston.pdponline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.doston.pdponline.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}

package uz.doston.pdponline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.doston.pdponline.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}

package uz.doston.pdponline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.doston.pdponline.entity.Warehouse;

import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    Optional<Warehouse> findByName(String name);
}

package uz.doston.pdponline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.doston.pdponline.entity.Currency;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    Optional<Currency> findByName(String name);
}

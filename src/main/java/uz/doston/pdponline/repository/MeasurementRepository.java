package uz.doston.pdponline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.doston.pdponline.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

}

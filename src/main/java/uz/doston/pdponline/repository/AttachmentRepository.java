package uz.doston.pdponline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.doston.pdponline.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}

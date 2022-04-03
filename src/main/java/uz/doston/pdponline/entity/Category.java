package uz.doston.pdponline.entity;

import lombok.*;
import uz.doston.pdponline.entity.base.Base;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true)
public class Category extends Base {

    @ManyToOne
    private Category parentCategory;
}

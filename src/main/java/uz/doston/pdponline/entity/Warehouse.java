package uz.doston.pdponline.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import uz.doston.pdponline.entity.base.Base;

import javax.persistence.Entity;

@AllArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true)
public class Warehouse extends Base {
}

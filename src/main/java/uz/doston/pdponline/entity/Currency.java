package uz.doston.pdponline.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import uz.doston.pdponline.entity.base.Base;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Currency extends Base {

}

package local.prac.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@Entity
public class AppRole {

    @Id @GeneratedValue
    private long rid;
    private String name;
}

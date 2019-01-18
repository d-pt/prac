package local.prac.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id @GeneratedValue private long uid;
    private String name;
    @ManyToMany private List<AppRole> roles;

}

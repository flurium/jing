package flurium.jing.db.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "jing_users")
public class JingUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
}

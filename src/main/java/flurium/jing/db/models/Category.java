package flurium.jing.db.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private List<Product> products;
}

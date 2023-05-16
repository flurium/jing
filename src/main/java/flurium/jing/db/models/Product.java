package flurium.jing.db.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String name;

    private Double price;

    @Column(columnDefinition = "text")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private JingUser user;

    @OneToMany
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private List<Property> properties;
}

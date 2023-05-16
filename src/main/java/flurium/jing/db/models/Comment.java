package flurium.jing.db.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private LocalDate date;

    private int grade;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Column(nullable = true)
    @Nullable
    private JingUser user;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @OneToMany
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    private List<Answer> answers;
}

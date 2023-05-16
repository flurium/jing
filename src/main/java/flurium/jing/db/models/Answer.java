package flurium.jing.db.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String content;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Column(nullable = true)
    @Nullable
    private JingUser user;
}

package flurium.jing.db.repositories;

import flurium.jing.db.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}

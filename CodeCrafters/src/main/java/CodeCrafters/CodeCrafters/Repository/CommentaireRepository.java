package CodeCrafters.CodeCrafters.Repository;

import CodeCrafters.CodeCrafters.model.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
}

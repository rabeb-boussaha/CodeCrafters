package CodeCrafters.CodeCrafters.Repository;

import CodeCrafters.CodeCrafters.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {
}

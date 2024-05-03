package CodeCrafters.CodeCrafters.Repository;

import CodeCrafters.CodeCrafters.model.Article;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Article a SET a.title = :title, a.description = :description WHERE a.id = :id")
    void updateArticle(@Param("id") Long id, @Param("title") String title, @Param("description") String description);
}

package CodeCrafters.CodeCrafters.Services;

import CodeCrafters.CodeCrafters.Repository.ArticleRepository;
import CodeCrafters.CodeCrafters.model.Article;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    @Override
    public List<Article> retrieveAll() {
        return articleRepository.findAll();
    }


    @Override
    public Article addArticle(Article a) {
        if (a.getTitle() == null || a.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Article title cannot be empty");
        }
        try {
            return articleRepository.save(a);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add Article", e);
        }


    }
// delete article

    @Override
    public void deletearticle(Long id) {
        Optional<Article> article = articleRepository.findById(id);

        article.ifPresent(a -> {
            articleRepository.delete(a);
            log.info("User with id " + id + " has been deleted");
        });
    }


    // edit article

    @Override
    public Article editArticle(Article a) throws RuntimeException {
        if (a.getId() == null) {
            throw new IllegalArgumentException("Article ID cannot be null for editing");
        }
        try {
            // Vérifiez si l'article existe dans la base de données
            Article existingArticle = articleRepository.findById(Long.valueOf(a.getId()))
                    .orElseThrow(() -> new IllegalArgumentException("Article not found"));

            // Mettez à jour les champs de l'article existant avec les nouvelles valeurs
            articleRepository.updateArticle(Long.valueOf(a.getId()), a.getTitle(), a.getDescription());

            // Retournez l'article mis à jour
            return existingArticle;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update article", e);
        }
    }
}




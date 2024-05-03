package CodeCrafters.CodeCrafters.Services;

import CodeCrafters.CodeCrafters.Repository.ArticleRepository;
import CodeCrafters.CodeCrafters.model.Article;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
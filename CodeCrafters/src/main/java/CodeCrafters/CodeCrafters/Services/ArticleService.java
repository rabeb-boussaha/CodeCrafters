package CodeCrafters.CodeCrafters.Services;

import CodeCrafters.CodeCrafters.model.Article;

import java.util.List;

public interface ArticleService {
    List<Article> retrieveAll();

    default Article addArticle(Article a) {
        return null;
    }

    void deletearticle(Long id);

    Article editArticle(Article a) throws RuntimeException;
}

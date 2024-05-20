package CodeCrafters.CodeCrafters.Services;

import CodeCrafters.CodeCrafters.Repository.ArticleRepository;
import CodeCrafters.CodeCrafters.Repository.CommentaireRepository;
import CodeCrafters.CodeCrafters.model.Article;
import CodeCrafters.CodeCrafters.model.Commentaire;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CommentaireServiceImpl implements CommentaireService {
    @Autowired
    private CommentaireRepository commentaireRepository;

    @Autowired
    private ArticleRepository articleRepository;




    @Override
    public List<Commentaire> getCommentsByArticleId(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found"));
        return article.getCommentaires(); // Assurez-vous que la méthode getCommentaires renvoie une liste de commentaires
    }
    @Override
    public Commentaire saveComment(Long articleId, Commentaire comment) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found"));
        comment.setArticle(article);
        return commentaireRepository.save(comment);
    }
}

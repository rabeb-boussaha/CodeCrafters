package CodeCrafters.CodeCrafters.Services;

import CodeCrafters.CodeCrafters.model.Commentaire;

import java.util.List;

public interface CommentaireService {

    List<Commentaire> getCommentsByArticleId(Long articleId);
    Commentaire saveComment(Long articleId, Commentaire comment);


}

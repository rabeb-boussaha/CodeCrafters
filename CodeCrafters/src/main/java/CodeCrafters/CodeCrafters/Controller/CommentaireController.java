package CodeCrafters.CodeCrafters.Controller;

import CodeCrafters.CodeCrafters.Services.CommentaireService;
import CodeCrafters.CodeCrafters.model.Commentaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/article/{articleId}/comments")
public class CommentaireController {
@Autowired
    private CommentaireService commentaireService;


    @GetMapping("/all-comment")
    public List<Commentaire> getCommentsByArticleId(@PathVariable Long articleId){
        return commentaireService.getCommentsByArticleId(articleId);
    }
    @PostMapping("/add-comment")
    public Commentaire createComment(@PathVariable Long articleId, @RequestBody Commentaire comment){
    return commentaireService.saveComment(articleId, comment);
    }


}

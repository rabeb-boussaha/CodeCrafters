package CodeCrafters.CodeCrafters.Controller;

import CodeCrafters.CodeCrafters.Services.ArticleService;
import CodeCrafters.CodeCrafters.model.Article;
import CodeCrafters.CodeCrafters.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/article")

public class ArticleController {

    private ArticleService articleService;
    // Injectez ArticleServiceImpl via le constructeur
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    //get all the article in the table in our database
    @GetMapping("/articles")
    public List<Article> getArticles() {
        return articleService.retrieveAll();
    }



    // add Article
    @PostMapping("/add-article")
    public Article addArticle(@RequestBody Article a) {
        return articleService.addArticle(a);
    }
// delete article
    @DeleteMapping("/delete-article/{id}")
    public void deleteArticle(@PathVariable("id") Long id) {
        articleService.deletearticle(id);
    }

    // edit  article
    @PutMapping ("/edit-article ")
    public Article editArticle(@RequestBody Article a) {
        return articleService.editArticle(a);
    }



    }

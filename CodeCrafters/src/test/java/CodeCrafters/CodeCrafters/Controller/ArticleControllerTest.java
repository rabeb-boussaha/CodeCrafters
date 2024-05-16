package CodeCrafters.CodeCrafters.Controller;

import CodeCrafters.CodeCrafters.Services.ArticleService;
import CodeCrafters.CodeCrafters.Services.ArticleServiceImpl;
import CodeCrafters.CodeCrafters.model.Article;
import CodeCrafters.CodeCrafters.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static io.micrometer.core.instrument.binder.http.HttpRequestTags.status;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ArticleService articleService;



    @BeforeEach
    void setUp() {
    }

    @Test
    void getArticles() {
        // Mock de service
        ArticleService articleService = Mockito.mock(ArticleService.class);
        ArticleController articleController= new ArticleController(articleService);




        // Création de la liste des articles attendue
        List<Article> articles = new ArrayList<>();
        articles.add(Article.builder()
                .id(1)
                .commentaire("Commentaire de l'article")
                .description("Description de l'article")
                .title("Titre de l'article")
                .vote("Vote de l'article")
                .build());

        articles.add(Article.builder()
                .id(2)
                .commentaire("Commentaire de l'article2")
                .description("Description de l'article2")
                .title("Titre de l'article 2")
                .vote("Vote de l'article2")
                .build());

        // Mock la methode all du service pour retourner la liste des articles
        when(articleService.retrieveAll()).thenReturn(articles);
        //Appl de la methode get Article( de controleur
        List<Article>retrievedArticle = articleController.getArticles();
        //Assertion
        // vérification que la liste retounrée n'est pas nulle
        assertNotNull(retrievedArticle);
        // vérification de la taille de la liste retournée
        assertEquals(articles.size(),retrievedArticle.size());
        // verification des attributs des articles
        for (int i=0; i< articles.size();i++){
            Article expectedArticle = articles.get(i);
            Article actualArticle= retrievedArticle.get(i);
            assertEquals(expectedArticle.getId(), actualArticle.getId());
            assertEquals(expectedArticle.getTitle(), actualArticle.getTitle());
            assertEquals(expectedArticle.getCommentaire(), actualArticle.getCommentaire());
            assertEquals(expectedArticle.getDescription(), actualArticle.getDescription());
        }




    }

    @Test
    void addArticle() {
    }

    @Test
    void deleteArticle() {
    }

    @Test
    void editArticle() {
    }
}
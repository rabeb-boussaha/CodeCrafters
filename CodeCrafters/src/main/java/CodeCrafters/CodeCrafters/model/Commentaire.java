package CodeCrafters.CodeCrafters.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name="Commentaire")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commentaire {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String contenu;

    @ManyToOne // Many comments can belong to one article
    @JoinColumn(name = "article_id") // Name of the column in Commentaire table that refers to Article
    private Article article; // Reference to the Article to which this Commentaire belongs

    @ManyToOne // Many comments can be created by one user
    @JoinColumn(name = "user_id") // Name of the column in Commentaire table that refers to User
    private User user; // Reference to the User who created this Commentaire




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

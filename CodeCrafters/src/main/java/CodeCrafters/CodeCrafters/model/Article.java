package CodeCrafters.CodeCrafters.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="Articles")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String description;
    private String commentaire;
    private  String vote;

    @ManyToOne // Many articles can belong to one user
    @JoinColumn(name = "user_id") // Name of the column in Article table that refers to User
    private User user; // Reference to the User that owns this Article


    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Categorie> categories; // Liste des catégories associées à cet article
}


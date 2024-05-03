package CodeCrafters.CodeCrafters.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="Catégorie")
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private  String title;
    private String description;



    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
}

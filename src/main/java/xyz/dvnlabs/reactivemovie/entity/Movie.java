package xyz.dvnlabs.reactivemovie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("movie")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    private Long id;

    @Column("movie_name")
    private String movieName;
    private String publisher;
    private String cover;
    private String synopsis;

    @Transient
    private List<Comment> comments;

}

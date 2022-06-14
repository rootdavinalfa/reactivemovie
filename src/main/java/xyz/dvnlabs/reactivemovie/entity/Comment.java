package xyz.dvnlabs.reactivemovie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    private Long id;

    @Column("parent")
    private Long parent;

    @Column("movie_id")
    private Long movieId;

    @Column("comment_by")
    private String commentBy;

    @Column("comment_content")
    private String commentContent;


}

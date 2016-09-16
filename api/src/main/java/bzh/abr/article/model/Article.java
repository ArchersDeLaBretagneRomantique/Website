package bzh.abr.article.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "articles")
@Getter @Setter
@EqualsAndHashCode
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "articles_id_seq", sequenceName = "articles_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="articles_id_seq")
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String title;

    @NotEmpty
    @Column(nullable = false)
    private String content;

    @JsonIgnore
    @Column(nullable = false)
    private boolean enabled;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date creationDate;

}

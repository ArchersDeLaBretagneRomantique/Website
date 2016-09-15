package bzh.abr.album.model;

import bzh.abr.album.photo.model.Photo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@EqualsAndHashCode
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @JsonIgnore
    @Column(nullable = false)
    private boolean activated;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private List<Photo> photos;

}

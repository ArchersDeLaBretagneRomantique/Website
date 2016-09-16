package bzh.abr.album.photo.model;

import bzh.abr.album.model.Album;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "photos")
@Getter @Setter
@EqualsAndHashCode
public class Photo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "photos_id_seq", sequenceName = "photos_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="photos_id_seq")
    private Long id;

    @Column(nullable = false)
    private String file;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

}

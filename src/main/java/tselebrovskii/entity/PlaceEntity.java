package tselebrovskii.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность города
 */
@Entity
@Table(name = "places")
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "photo")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] photo;

    @OneToMany(mappedBy = "place")
    List<TourEntity> tours;

}

package tselebrovskii.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Time;

/**
 * Сущность экскурсии
 */
@Entity
@Table(name = "tours")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "duration", nullable = false, columnDefinition = "TIME")
    private Time duration;

    @Column(name = "costs", nullable = false, columnDefinition = "BIGINT")
    private BigInteger costs;

    @Column(name = "Tour_days", nullable = false, columnDefinition = "TEXT")
    private String tourDays;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "guide_id", nullable = false)
    GuideEntity guide;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    PlaceEntity place;

}

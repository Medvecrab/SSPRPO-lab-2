package tselebrovskii.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

/**
 * Сущность экскурсии
 */
@Entity
@Table(name = "guide")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuideEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "surname", nullable = false, length = 30)
    private String surname;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "Middle_name", length = 30)
    private String middleName;

    @Column(name = "Work_days", nullable = false, columnDefinition = "TEXT")
    private String workDays;

    @Column(name = "salary", nullable = false, columnDefinition = "BIGINT")
    private BigInteger salary;

    @OneToMany(mappedBy = "guide")
    List<TourEntity> tours;

}

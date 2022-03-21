package tselebrovskii.form;

import lombok.*;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TourForm {

    private Long id;

    private String name;

    private String description;

    private String tourDays;

    private String duration;

    private BigInteger costs;

    private Long guideId;

    private Long placeId;

}

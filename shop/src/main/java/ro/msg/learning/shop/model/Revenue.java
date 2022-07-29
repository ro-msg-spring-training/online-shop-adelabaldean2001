package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Revenue extends BaseEntity{
    @ManyToOne(fetch = FetchType.EAGER)
    private Location location;
    private LocalDateTime date;
    private BigDecimal sum;
}

package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer extends BaseEntity{
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    @Column(name = "email")
    private String emailAddress;

}

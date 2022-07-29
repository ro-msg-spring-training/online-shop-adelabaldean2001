package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity{
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String emailAddress;
}

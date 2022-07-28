package ro.msg.learning.shop.Model;

import lombok.*;

import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer extends BaseEntity{
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String emailAddress;
}

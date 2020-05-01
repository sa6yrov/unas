package kg.onlinestore.unas.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", unique = true)
    String login;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "email", unique = true)
    String email;

    @Column(name = "is_active", nullable = false)
    boolean isActive;

}

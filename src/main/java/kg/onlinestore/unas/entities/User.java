package kg.onlinestore.unas.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login",nullable = false, unique = true)
    String login;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "email",nullable = false, unique = true)
    String email;

    @Column(name = "is_active", nullable = false)
    boolean isActive;

}

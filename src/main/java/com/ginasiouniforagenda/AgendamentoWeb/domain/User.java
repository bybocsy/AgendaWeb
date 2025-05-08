package com.ginasiouniforagenda.AgendamentoWeb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String occupation;

    @Override
    public String toString() {
        return "User {" + "\n" +
                "Id: " + id + "\n" +
                "name: " + name + "\n" +
                "email: " + email + "\n" +
                "password: " + password + "\n" +
                "occupation: " + occupation +
                '}';
    }
}

package com.example.demo.models.user;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    @ManyToOne
    private Role role;
}



package com.library.e_library.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@Component
public class User {
    @Id
    private Integer id;
    @Column(unique = true)
    private String username;
    private String password;
    private String role;
}

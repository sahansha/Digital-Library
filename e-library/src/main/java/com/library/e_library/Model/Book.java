package com.library.e_library.Model;

import com.library.e_library.enums.Category;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String author;
    @Column(unique = true)
    private String isbn;
    private double price;
    private String description;
    private Category category;
}

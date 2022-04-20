package com.example.bookapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name,authors;


    @ManyToOne
    private Language language;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private Attachment img;

    @ManyToOne(cascade = CascadeType.ALL)
    private Attachment file;

    public Book(String name, Language language, String description, String authors) {
        this.name = name;
        this.language = language;
        this.description = description;
        this.authors = authors;
    }

}

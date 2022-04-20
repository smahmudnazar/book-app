package com.example.bookapp.repository;

import com.example.bookapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {


    @Query(nativeQuery = true,value = "select id, authors, description, book.name, file_id, img_id, language_id from book ;")
    List<Book> getAllBooks();


}

package com.example.bookapp.controller;

import com.example.bookapp.dto.ApiResponse;
import com.example.bookapp.dto.BookDTO;
import com.example.bookapp.entity.Book;
import com.example.bookapp.repository.BookRepository;
import com.example.bookapp.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final BookService bookService;

//    @PreAuthorize("hasAnyAuthority('ADMIN','USER','SUPER_ADMIN')")
//    @DeleteMapping("/getAll")
//    private ResponseEntity<?> getAllDeleteMaping(){
//        return ResponseEntity.ok().body(bookRepository.findAll());
//    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER','SUPER_ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok().body(bookRepository.findAll());
    }


//    @PreAuthorize("hasAnyAuthority('ADMIN','USER','SUPER_ADMIN')")
//    @GetMapping("/{id}")
//    private ResponseEntity<?> getOne(@PathVariable Integer id){
//
//        Optional<Book> bookOptional = bookRepository.findById(id);
//        if (bookOptional.isEmpty()) return ResponseEntity.notFound().build();
//
//        return ResponseEntity.ok().body(bookOptional.get());
//    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER','SUPER_ADMIN')")
    @DeleteMapping("/getOne/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id){
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(bookOptional.get());
    }



    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) return ResponseEntity.notFound().build();
        bookRepository.delete(bookOptional.get());

        return ResponseEntity.ok().body("Deleted");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> add(@ModelAttribute BookDTO book){
        ApiResponse apiResponse = bookService.save(book);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id,@ModelAttribute BookDTO book){
        ApiResponse apiResponse = bookService.edit(id, book);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

}

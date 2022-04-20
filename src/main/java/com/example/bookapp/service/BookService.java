package com.example.bookapp.service;

import com.example.bookapp.dto.ApiResponse;
import com.example.bookapp.dto.BookDTO;
import com.example.bookapp.entity.Attachment;
import com.example.bookapp.entity.Book;
import com.example.bookapp.entity.Language;
import com.example.bookapp.repository.BookRepository;
import com.example.bookapp.repository.LanguageRepository;;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    final BookRepository bookRepository;
    final LanguageRepository languageRepository;

    @SneakyThrows
    public ApiResponse save(BookDTO bookDTO) {
        Book book=new Book();
        book.setName(bookDTO.getName());
        book.setDescription(bookDTO.getDescription());
        book.setAuthors(bookDTO.getAuthors());

        Optional<Language> byLanguageEnum = languageRepository.findById(bookDTO.getLanguage_id());
        if (byLanguageEnum.isEmpty()) {
            return new ApiResponse("Language not found",false);
        }

        Attachment img=new Attachment();
        img.setName(bookDTO.getImg().getOriginalFilename());
        img.setContentType(bookDTO.getImg().getContentType());
        img.setSize(bookDTO.getImg().getSize());
        img.setBytes(bookDTO.getImg().getBytes());

        book.setImg(img);

        Attachment file=new Attachment();
        file.setName(bookDTO.getFile().getOriginalFilename());
        file.setContentType(bookDTO.getFile().getContentType());
        file.setSize(bookDTO.getFile().getSize());
        file.setBytes(bookDTO.getFile().getBytes());

        book.setFile(file);

        book.setLanguage(byLanguageEnum.get());

        bookRepository.save(book);

        return new ApiResponse("Added!",true);
    }


    @SneakyThrows
    public ApiResponse edit(Integer id, BookDTO bookDTO) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) return new ApiResponse("Not found",false);

        Book book = bookOptional.get();
        book.setAuthors(bookDTO.getAuthors());
        book.setDescription(bookDTO.getDescription());
        book.setName(bookDTO.getName());


        Optional<Language> byLanguageEnum = languageRepository.findById(bookDTO.getLanguage_id());
        if (byLanguageEnum.isEmpty()) {
            return new ApiResponse("Language not found",false);
        }

        Attachment img=new Attachment();
        img.setName(bookDTO.getImg().getOriginalFilename());
        img.setContentType(bookDTO.getImg().getContentType());
        img.setSize(bookDTO.getImg().getSize());
        img.setBytes(bookDTO.getImg().getBytes());

        book.setImg(img);

        Attachment file=new Attachment();
        file.setName(bookDTO.getFile().getOriginalFilename());
        file.setContentType(bookDTO.getFile().getContentType());
        file.setSize(bookDTO.getFile().getSize());
        file.setBytes(bookDTO.getFile().getBytes());

        book.setFile(file);

        book.setLanguage(byLanguageEnum.get());

        bookRepository.save(book);

        return new ApiResponse("Edited",true);
    }

}

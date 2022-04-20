package com.example.bookapp.component;

import com.example.bookapp.entity.Book;
import com.example.bookapp.entity.Language;
import com.example.bookapp.entity.User;
import com.example.bookapp.entity.enums.LanguageEnum;
import com.example.bookapp.entity.enums.RoleEnum;
import com.example.bookapp.repository.BookRepository;
import com.example.bookapp.repository.LanguageRepository;
import com.example.bookapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;
    final BookRepository bookRepository;
    final LanguageRepository languageRepository;


    @Value("${spring.sql.init.mode}")
    String mode;


    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")) {


            User admin=new User("admin", passwordEncoder.encode("admin"), RoleEnum.ADMIN);
            User user=new User("user", passwordEncoder.encode("user"), RoleEnum.USER);
            User superAdmin=new User("superAdmin", passwordEncoder.encode("superAdmin"), RoleEnum.SUPER_ADMIN);

            userRepository.save(admin);
            userRepository.save(user);
            userRepository.save(superAdmin);


            Language english = languageRepository.save(new Language(LanguageEnum.ENGLISH));
            Language russian = languageRepository.save(new Language(LanguageEnum.RUSSIAN));
            Language uzbek = languageRepository.save(new Language(LanguageEnum.UZBEK));

            bookRepository.save(new Book("Algebra",english,"Zor kitob","Abdulla Oripov"));
            bookRepository.save(new Book("Matamatika",russian,"Zor kitob","Abdulla Oripov"));
            bookRepository.save(new Book("Uzbek tili",uzbek,"Zor kitob","Abdulla Oripov"));


        }
    }
}

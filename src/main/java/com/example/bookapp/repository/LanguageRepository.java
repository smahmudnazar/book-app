package com.example.bookapp.repository;

import com.example.bookapp.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LanguageRepository extends JpaRepository<Language,Integer> {
}

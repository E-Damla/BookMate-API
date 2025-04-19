package com.example.BM.Books;

import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BookRepo extends JpaRepository<BookEntity,Long> {
    List<BookEntity> findByNameContainingIgnoreCaseOrAuthorContainingIgnoreCase(String name, String author);
    List<BookEntity> findByPriceLessThanEqual(double price);
    List<BookEntity> findByCategoryContainingIgnoreCase(String category);
}

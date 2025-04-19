package com.example.BM.Books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public List<BookEntity> getAllBooks() {
        return bookRepo.findAll();
    }

    public BookEntity addBook(BookEntity book) {
        return bookRepo.save(book);
    }

    public void deleteBook(Long id) {
        bookRepo.deleteById(id);
    }

    public BookEntity getBookById(Long id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book Not Found"));
    }

    public BookEntity updateBook(Long id, BookEntity updateBook) {
        updateBook.setId(id);
        return bookRepo.save(updateBook);
    }

    public BookEntity partialUpdate(Long id, Map<String, Object> updates) {
        BookEntity book = getBookById(id);
        if (updates.containsKey("name")) {
            book.setName((String) updates.get("name"));
        }
        if (updates.containsKey("price")) {
            Object priceValue = updates.get("price");
            if (priceValue instanceof Number) {
                book.setPrice(((Number) priceValue).doubleValue());
            } else {
                book.setPrice(Double.parseDouble(priceValue.toString()));
            }
        }
        if (updates.containsKey("category")) {
            book.setCategory((String) updates.get("category"));
        }
        if (updates.containsKey("publicationYear")) {
            Object year = updates.get("publicationYear");
            if (year instanceof Number) {
                book.setPublicationYear(((Number) year).intValue());
            } else {
                book.setPublicationYear(Integer.parseInt(year.toString()));
            }
        }
        return bookRepo.save(book);
    }
    public List<BookEntity> searchByCategory(String category) {
        return bookRepo.findByCategoryContainingIgnoreCase(category);
    }
    public List<BookEntity> searchBooks(String keyword) {
        return bookRepo.findByNameContainingIgnoreCaseOrAuthorContainingIgnoreCase(keyword, keyword);
    }
    public List<BookEntity> searchByPrice(double price) {
        return bookRepo.findByPriceLessThanEqual(price);
    }

    public List<BookEntity> getBooksSortedByPrice(String order) {
        if ("asc".equalsIgnoreCase(order)) {
            return bookRepo.findAll(Sort.by(Sort.Direction.ASC, "price"));
        } else if ("desc".equalsIgnoreCase(order)) {
            return bookRepo.findAll(Sort.by(Sort.Direction.DESC, "price"));
        } else {
            throw new IllegalArgumentException("Sıralama yalnızca 'asc' veya 'desc' olabilir.");
        }
    }
}
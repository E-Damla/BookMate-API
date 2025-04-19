package com.example.BM.Books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookEntity> getAllProducts(){
        return bookService.getAllBooks();
    }
    @PostMapping
    public BookEntity createBook(@RequestBody BookEntity book){
        return bookService.addBook(book);
    }
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }
    @GetMapping("/{id}")
    public BookEntity getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }
    @GetMapping("/sorted")
    public List<BookEntity> getBooksSortedByPrice(@RequestParam String order) {
        return bookService.getBooksSortedByPrice(order);
    }
    @PutMapping("/{id}")
    public BookEntity updateBook(@PathVariable Long id,@RequestBody BookEntity updateBook){
        return bookService.updateBook(id,updateBook);
    }
    @PatchMapping("/{id}")
    public BookEntity partialUpdate(@PathVariable Long id, @RequestBody Map<String,Object> updates){
       return  bookService.partialUpdate(id,updates);
    }
    @GetMapping("/search")
    public List<BookEntity> searchBooks(@RequestParam String keyword) {
        return bookService.searchBooks(keyword);
    }
    @GetMapping("/search/category")
    public List<BookEntity> searchByCategory(@RequestParam String category) {
        return bookService.searchByCategory(category);
    }
    @GetMapping("/search/price")
    public List<BookEntity> searchByPrice(@RequestParam double maxPrice) {
        return bookService.searchByPrice(maxPrice);
    }


}

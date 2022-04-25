package emt.lab.emt_lab_2_181046.controllers;

import emt.lab.emt_lab_2_181046.models.Book;
import emt.lab.emt_lab_2_181046.models.Category;
import emt.lab.emt_lab_2_181046.models.dto.BookDto;
import emt.lab.emt_lab_2_181046.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookControllerREST {

    public final BookService bookService;

    public BookControllerREST(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll(){
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        return this.bookService.findById(id).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto){
            return this.bookService.save(bookDto).map(book -> ResponseEntity.ok().body(book))
                    .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id, @RequestBody BookDto bookDto){
        return this.bookService.edit(id, bookDto).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){
             return this.bookService.delete(id).map(book -> ResponseEntity.ok().body(book))
                     .orElseGet(() -> ResponseEntity.badRequest().build());
    }

        @GetMapping("/markAsTaken/{id}")
    public ResponseEntity<Book> markAsTaken(@PathVariable Long id){

        return this.bookService.markAsTaken(id).map(book -> ResponseEntity.ok().body(book)).
                orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/allCategories")
    public List<Category> getAllCategories(){
        return Arrays.stream(Category.values()).toList();
    }
}

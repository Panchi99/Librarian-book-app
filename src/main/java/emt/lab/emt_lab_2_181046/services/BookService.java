package emt.lab.emt_lab_2_181046.services;

import emt.lab.emt_lab_2_181046.models.Book;
import emt.lab.emt_lab_2_181046.models.dto.BookDto;
import emt.lab.emt_lab_2_181046.models.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> save(String name, Category category, Long authorId, Integer availableCopies);
    Optional<Book> save(BookDto bookDto);
    Optional<Book> edit(Long bookId, String name, Category category, Long authorId, Integer availableCopies);
    Optional<Book> edit(Long bookId,BookDto bookDto);
    Optional<Book> delete(Long bookId);
    Optional<Book> markAsTaken(Long bookId);
}

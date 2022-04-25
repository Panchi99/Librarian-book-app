package emt.lab.emt_lab_2_181046.services.impl;

import emt.lab.emt_lab_2_181046.exceptions.AuthorNotFoundException;
import emt.lab.emt_lab_2_181046.exceptions.BookNotFoundException;
import emt.lab.emt_lab_2_181046.models.Author;
import emt.lab.emt_lab_2_181046.models.Book;
import emt.lab.emt_lab_2_181046.models.dto.BookDto;
import emt.lab.emt_lab_2_181046.models.Category;
import emt.lab.emt_lab_2_181046.repositories.AuthorRepository;
import emt.lab.emt_lab_2_181046.repositories.BookRepository;
import emt.lab.emt_lab_2_181046.services.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(String name, Category category, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(()-> new AuthorNotFoundException(authorId));
        Book book = new Book(name,category,author,availableCopies);

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthorId()).orElseThrow(()-> new AuthorNotFoundException(bookDto.getAuthorId()));
        Book book = new Book(bookDto.getName(),bookDto.getCategory(),author,bookDto.getAvailableCopies());

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long bookId, String name, Category category, Long authorId, Integer availableCopies) {
        Book book = this.bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long bookId,BookDto bookDto) {
        Book book = this.bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        Author author = this.authorRepository.findById(bookDto.getAuthorId()).orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthorId()));
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> delete(Long bookId) {
        Book book = this.bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        this.bookRepository.delete(book);
        return Optional.of(book);
    }

    @Override
    @Transactional
    public Optional<Book> markAsTaken(Long bookId) {
            this.bookRepository.markAsTaken(bookId);
            Book book = this.bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
            if (book.getAvailableCopies() <= 0) {
                this.bookRepository.delete(book);
                return Optional.empty();
            }
            return Optional.of(book);
    }
}

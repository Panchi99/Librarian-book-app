package emt.lab.emt_lab_2_181046.config;

import emt.lab.emt_lab_2_181046.models.Author;
import emt.lab.emt_lab_2_181046.models.Book;
import emt.lab.emt_lab_2_181046.models.Category;
import emt.lab.emt_lab_2_181046.models.Country;
import emt.lab.emt_lab_2_181046.repositories.AuthorRepository;
import emt.lab.emt_lab_2_181046.repositories.BookRepository;
import emt.lab.emt_lab_2_181046.repositories.CountryRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CountryRepository countryRepository;
    List<Author> authorList = new ArrayList<>();
    List<Book> bookList = new ArrayList<>();

    public DataInitializer(AuthorRepository authorRepository, BookRepository bookRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void init(){

        Random random = new Random();

        for(int i=1;i<=10;i++){
            Country country = new Country(String.format("Country %d",i),String.format("Continent %d",i));
            countryRepository.save(country);
            Author author = new Author(String.format("AuthorName %d",i),String.format("AuthorSurname %d",i),country);
            authorList.add(author);

            Category category = Arrays.stream(Category.values()).toList().get(random.nextInt(0,7));

            bookList.add(new Book(String.format("BookName %d",i),category,author,random.nextInt(1,21)));
        }
        authorRepository.saveAll(authorList);
        bookRepository.saveAll(bookList);
    }

}

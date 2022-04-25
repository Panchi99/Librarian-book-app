package emt.lab.emt_lab_2_181046.services.impl;

import emt.lab.emt_lab_2_181046.exceptions.AuthorNotFoundException;
import emt.lab.emt_lab_2_181046.exceptions.CountryNotFoundException;
import emt.lab.emt_lab_2_181046.models.Author;
import emt.lab.emt_lab_2_181046.models.Country;
import emt.lab.emt_lab_2_181046.repositories.AuthorRepository;
import emt.lab.emt_lab_2_181046.repositories.CountryRepository;
import emt.lab.emt_lab_2_181046.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    public final AuthorRepository authorRepository;
    public final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }


    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(String name, String surname, Country country) {
        Author author = new Author(name,surname,country);
        return Optional.of(authorRepository.save(author));
    }

    @Override
    public Optional<Author> edit(Long authorId, String name, String surname, Long countryId) {
        Country country = this.countryRepository.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId));
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);

        return Optional.of(authorRepository.save(author));
    }

    @Override
    public Optional<Author> delete(Long authorId) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        authorRepository.delete(author);
       return Optional.of(author);
    }
}

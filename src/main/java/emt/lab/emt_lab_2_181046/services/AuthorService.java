package emt.lab.emt_lab_2_181046.services;

import emt.lab.emt_lab_2_181046.models.Author;
import emt.lab.emt_lab_2_181046.models.Country;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
     List<Author> findAll();
     Optional<Author> findById(Long id);
     Optional<Author> save(String name, String surname, Country country);
     Optional<Author> edit(Long authorId,String name, String surname, Long countryId);
     Optional<Author> delete(Long authorId);
}

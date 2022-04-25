package emt.lab.emt_lab_2_181046.repositories;

import emt.lab.emt_lab_2_181046.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {


}

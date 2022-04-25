package emt.lab.emt_lab_2_181046.repositories;

import emt.lab.emt_lab_2_181046.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    @Modifying
    @Query("update Book b set b.availableCopies = b.availableCopies - 1 where b.id = :bookId")
    void markAsTaken(@Param("bookId") Long bookId);

}

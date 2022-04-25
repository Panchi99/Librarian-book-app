package emt.lab.emt_lab_2_181046.repositories;

import emt.lab.emt_lab_2_181046.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
}

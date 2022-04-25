package emt.lab.emt_lab_2_181046.exceptions;

public class CountryNotFoundException extends RuntimeException{
    public CountryNotFoundException(Long id) {
        super(String.format("Country with id: %d not found",id));
    }
}

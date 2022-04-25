package emt.lab.emt_lab_2_181046.exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long id) {
        super(String.format("Book with id %d not found",id));
    }
}

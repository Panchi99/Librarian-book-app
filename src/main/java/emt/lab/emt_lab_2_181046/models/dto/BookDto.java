package emt.lab.emt_lab_2_181046.models.dto;

import emt.lab.emt_lab_2_181046.models.Category;
import lombok.Data;

import javax.persistence.*;

@Data
public class BookDto {

    private String name;

    private Category category;

    private Long authorId;

    private Integer availableCopies;



    public BookDto() {
    }

    public BookDto(String name, String category, Long authorId, Integer availableCopies) {
        this.name = name;
        this.category = Category.valueOf(category);
        this.authorId = authorId;
        this.availableCopies = availableCopies;
    }
}

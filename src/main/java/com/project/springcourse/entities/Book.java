package com.project.springcourse.entities;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Book {
    int curernt_year;
    private Integer id;
    @NotEmpty(message = "Book name should not be empty!")
    @Size(min = 2, max = 256, message = "Book name should be between 2 and 256 characters!")
    private String book_name;
    @NotEmpty(message = "Author name should not be empty!")
    @Size(min = 2, max = 256, message = "Author name should be between 2 and 128 characters!")
    private String author;
    @Min(value = 0, message = "Year should not me less than 0!")
    @Max(value = 2030, message = "Year should be less than 2030!")
    private Integer year;
    private Integer person_id;
}

package com.project.springcourse.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Person {
    private int person_id;
    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 5, max = 128, message = "Name should be between 5 and 128 characters!")
    private String person_name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
}

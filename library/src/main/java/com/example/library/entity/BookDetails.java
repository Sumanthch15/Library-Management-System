package com.example.library.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDetails {

    private String bookId;

    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @NotEmpty(message = "Author cannot be empty")
    private String author;

    private String genre;

    @Pattern(regexp = "Available|Checked Out", message = "Availability status must be either 'Available' or 'Checked Out'")
    private String availabilityStatus;

}

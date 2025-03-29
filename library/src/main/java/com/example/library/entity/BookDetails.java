package com.example.library.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Class to store the Book details
public class BookDetails {


    //  default and parametrized constructors
    public BookDetails() {}

    public BookDetails(String id, String title, String author, String genre, String availabilityStatus) {
        this.bookId = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availabilityStatus = availabilityStatus;
    }

    // Generating the getters and setters
    private String bookId;

    @NotEmpty(message = "Title cannot be empty")       // making to add book title and not to be empty
    private String title;

    @NotEmpty(message = "Author cannot be empty")     //// making to add book author and not to be empty
    private String author;

    private String genre;

    @Pattern(regexp = "Available|Checked Out", message = "Availability status must be either 'Available' or 'Checked Out'")
    private String availabilityStatus;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
}

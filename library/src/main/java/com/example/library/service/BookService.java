package com.example.library.service;

import com.example.library.exception.BookIdAlreadyExistsException;
import com.example.library.exception.BookNotFoundException;
import org.springframework.stereotype.Service;
import com.example.library.entity.BookDetails;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BookService {

    private final Map<String, BookDetails> bookMap = new ConcurrentHashMap<>();


    public String addBook(BookDetails book) throws BookIdAlreadyExistsException {
        if (book.getBookId() == null || bookMap.containsKey(book.getBookId())) {
            throw new BookIdAlreadyExistsException("Book ID already exists or is invalid.");
        }
        bookMap.put(book.getBookId(), book);
        return "Book added successfully.";
    }


    public Collection<BookDetails> getAllBooks() {
        return bookMap.values();
    }


    public BookDetails getBookById(String id) throws BookNotFoundException {
        BookDetails book = bookMap.get(id);
        if (book == null) {
            throw new BookNotFoundException("Book not found with ID: " + id);
        }
        return book;
    }


    public List<BookDetails> searchBooksByTitle(String title) {
        List<BookDetails> results = new ArrayList<>();
        for (BookDetails book : bookMap.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                results.add(book);
            }
        }
        return results;
    }


    public String updateBook(String id, BookDetails updatedBook) throws BookNotFoundException {
        BookDetails existing = bookMap.get(id);
        if (existing == null) throw new BookNotFoundException("Book not found.");

        if (!updatedBook.getTitle().isEmpty()) existing.setTitle(updatedBook.getTitle());
        if (!updatedBook.getAuthor().isEmpty()) existing.setAuthor(updatedBook.getAuthor());
        if (!updatedBook.getGenre().isEmpty()) existing.setGenre(updatedBook.getGenre());

        String status = updatedBook.getAvailabilityStatus();
        if ("Available".equalsIgnoreCase(status) || "Checked Out".equalsIgnoreCase(status)) {
            existing.setAvailabilityStatus(status);
        }

        return "Book updated successfully.";
    }


    public String deleteBook(String id) throws BookNotFoundException {
        if (bookMap.remove(id) != null) return "Book deleted successfully.";
        throw new BookNotFoundException("Book not found.");
    }

}

package com.example.library.controller;


import com.example.library.entity.BookDetails;
import com.example.library.exception.BookIdAlreadyExistsException;
import com.example.library.exception.BookNotFoundException;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//Used controller annotation for Thymeleaf
@Controller
public class BookController {

    @Autowired
    private BookService bookService;         // injected the service class


    //  home page
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("book", new BookDetails());
        model.addAttribute("books", bookService.getAllBooks());
        return "index";
    }

    //to add  book
    @PostMapping("/add")
    public String addBook(@ModelAttribute BookDetails book, Model model) {
        try {
            bookService.addBook(book);
        } catch (BookIdAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("book", new BookDetails());
        model.addAttribute("books", bookService.getAllBooks());
        return "index";
    }


    // tp update a book
    @PostMapping("/update")
    public String updateBook(@ModelAttribute BookDetails book, Model model) {
        try {
            bookService.updateBook(book.getBookId(), book);
        } catch (BookNotFoundException e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("book", new BookDetails());
        model.addAttribute("books", bookService.getAllBooks());
        return "index";
    }


    //to delete a book
    @PostMapping("/delete")
    public String deleteBook(@RequestParam String id, Model model) {
        try {
            bookService.deleteBook(id);
        } catch (BookNotFoundException e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("book", new BookDetails());
        model.addAttribute("books", bookService.getAllBooks());
        return "index";
    }

    //to search a book
    @PostMapping("/search")
    public String searchBook(@RequestParam String searchTitle, Model model) {
        List<BookDetails> results = bookService.searchBooksByTitle(searchTitle);
        model.addAttribute("book", new BookDetails());
        model.addAttribute("books", results);
        return "index";
    }
}

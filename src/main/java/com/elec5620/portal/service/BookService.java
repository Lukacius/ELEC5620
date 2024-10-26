package com.elec5620.portal.service;

import com.opencsv.CSVReader;
import com.elec5620.portal.repository.BookRepository;
import com.elec5620.portal.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private static BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        BookService.bookRepository = bookRepository;
    }

    public static void saveCsvToDatabase(MultipartFile file) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] line;
            reader.readNext();

            while ((line = reader.readNext()) != null) {
                Book book = getBook(line);
                bookRepository.save(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Book getBook(String[] line) {
        Book book = new Book();
        book.setTitle(line[1]);
        book.setAuthors(line[2]);
        book.setAverageRating(Double.parseDouble(line[3]));
        book.setIsbn(line[4]);
        book.setIsbn13(line[5]);
        book.setLanguageCode(line[6]);
        book.setNumPages(Integer.parseInt(line[7]));
        book.setRatingsCount(Integer.parseInt(line[8]));
        book.setTextReviewsCount(Integer.parseInt(line[9]));
        book.setPublicationDate(line[10]);
        book.setPublisher(line[11]);
        return book;
    }
}

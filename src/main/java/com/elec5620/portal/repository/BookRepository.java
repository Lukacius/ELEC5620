package com.elec5620.portal.repository;

import com.elec5620.portal.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

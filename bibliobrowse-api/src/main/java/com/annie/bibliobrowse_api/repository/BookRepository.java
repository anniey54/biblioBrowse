package com.annie.bibliobrowse_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.annie.bibliobrowse_api.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

  @Query("SELECT b FROM Book b JOIN Author a ON b.author = a.id WHERE LOWER(b.title) LIKE %:text% OR LOWER(a.fullName) LIKE %:text%")
  Optional<List<Book>> findByTitleAndAuthor(String text);
}

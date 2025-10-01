package com.annie.bibliobrowse_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.annie.bibliobrowse_api.entity.Author;
import com.annie.bibliobrowse_api.entity.Book;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

  @Query("SELECT book FROM Book book WHERE book.author = :authorId")
  Optional<List<Book>> findByIdWithBooks(Long authorId);
}

package com.annie.bibliobrowse_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.annie.bibliobrowse_api.composite_key.UserBookId;
import com.annie.bibliobrowse_api.entity.Book;
import com.annie.bibliobrowse_api.entity.UserFavouriteBook;

@Repository
public interface UserFavouriteBookRepository extends CrudRepository<UserFavouriteBook, UserBookId> {

  @Query("SELECT book FROM Book book, UserFavouriteBook userFavouriteBook  WHERE userFavouriteBook.user.id = :userId AND book.id = userFavouriteBook.book.id")
  Optional<List<Book>> findFavouriteBooksByUserId(Long userId);
}

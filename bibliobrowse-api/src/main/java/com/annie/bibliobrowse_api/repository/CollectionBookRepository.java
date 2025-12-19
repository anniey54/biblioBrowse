package com.annie.bibliobrowse_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.annie.bibliobrowse_api.composite_key.CollectionBookId;
import com.annie.bibliobrowse_api.entity.Book;
import com.annie.bibliobrowse_api.entity.CollectionBook;

@Repository
public interface CollectionBookRepository extends CrudRepository<CollectionBook, CollectionBookId> {

  @Query("SELECT book FROM Book book, CollectionBook collectionBook  WHERE collectionBook.collection.id = :collectionId AND book.id = collectionBook.book.id")
  Optional<List<Book>> findBooksByCollectionId(Long collectionId);
}

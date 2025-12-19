package com.annie.bibliobrowse_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.annie.bibliobrowse_api.composite_key.CollectionBookId;
import com.annie.bibliobrowse_api.dto.CollectionBookDTO;
import com.annie.bibliobrowse_api.entity.Book;
import com.annie.bibliobrowse_api.entity.Collection;
import com.annie.bibliobrowse_api.entity.CollectionBook;
import com.annie.bibliobrowse_api.repository.BookRepository;
import com.annie.bibliobrowse_api.repository.CollectionBookRepository;
import com.annie.bibliobrowse_api.repository.CollectionRepository;

@Service
public class CollectionBookServiceImpl implements CollectionBookService {
  @Autowired
  private CollectionBookRepository collectionBookRepository;

  @Autowired
  private CollectionRepository collectionRepository;

  @Autowired
  private BookRepository bookRepository;

  @Override
  public List<Book> getBooksByCollectionId(Long collectionId) {
    List<Book> books = collectionBookRepository.findBooksByCollectionId(collectionId)
      .orElseThrow(() -> new RuntimeException("Books in collection not found"));
    return books;
  }

  @Override
  public CollectionBookDTO createCollectionBook(Long collectionId, Long bookId) {
    // find collection and book by their ids
    Collection collection = collectionRepository.findById(collectionId).orElseThrow(() -> new RuntimeException("Collection not found"));
    Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
    
    // Create new CollectionBook object
    CollectionBook newCollectionBook = new CollectionBook();
    newCollectionBook.setId(new CollectionBookId(collectionId, bookId));
    newCollectionBook.setCollection(collection);
    newCollectionBook.setBook(book);
    collectionBookRepository.save(newCollectionBook);
    CollectionBookDTO cbDTO = new CollectionBookDTO(collectionId, bookId);
    return cbDTO;
  }

  @Override
  public String deleteCollectionBook(CollectionBookId id) {
    if (collectionBookRepository.existsById(id)) {
      collectionBookRepository.deleteById(id);
      return "Book id " + id.getBookId() + " from collection id " + id.getCollectionId() + " is deleted Successfully";
    }
    return "Failed to delete book id " + id.getBookId() + " from collection id " + id.getCollectionId();
  }

}

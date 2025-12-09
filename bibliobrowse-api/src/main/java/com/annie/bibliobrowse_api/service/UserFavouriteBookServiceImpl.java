package com.annie.bibliobrowse_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.annie.bibliobrowse_api.composite_key.UserBookId;
import com.annie.bibliobrowse_api.dto.UserBookDTO;
import com.annie.bibliobrowse_api.entity.Book;
import com.annie.bibliobrowse_api.entity.User;
import com.annie.bibliobrowse_api.entity.UserFavouriteBook;
import com.annie.bibliobrowse_api.repository.BookRepository;
import com.annie.bibliobrowse_api.repository.UserFavouriteBookRepository;
import com.annie.bibliobrowse_api.repository.UserRepository;

@Service
public class UserFavouriteBookServiceImpl implements UserFavouriteBookService {
  @Autowired
  private UserFavouriteBookRepository userFavouriteBookRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BookRepository bookRepository;

  @Override
  public List<Book> getBooksByUserId(Long userId) {
    List<Book> books = userFavouriteBookRepository.findFavouriteBooksByUserId(userId)
      .orElseThrow(() -> new RuntimeException("Favourite books not found"));
    return books;
  }

  @Override
  public UserBookDTO createFavouriteBook(Long userId, Long bookId) {
    // find user and book by their ids
    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
    
    // Create new UserFavouriteBook object
    UserFavouriteBook newUserFavouriteBook = new UserFavouriteBook();
    newUserFavouriteBook.setId(new UserBookId(userId, bookId));
    newUserFavouriteBook.setUser(user);
    newUserFavouriteBook.setBook(book);
    userFavouriteBookRepository.save(newUserFavouriteBook);
    UserBookDTO ubDTO = new UserBookDTO(userId, bookId);
    return ubDTO;
  }

  @Override
  public String deleteFavouriteBook(UserBookId id) {
    if (userFavouriteBookRepository.existsById(id)) {
      userFavouriteBookRepository.deleteById(id);
      return "Favourite book id " + id.getBookId() + " of user id " + id.getUserId() + " is deleted Successfully";
    }
    return "Failed to delete favourite book id " + id.getBookId() + " of user id " + id.getUserId();
  }
  
}

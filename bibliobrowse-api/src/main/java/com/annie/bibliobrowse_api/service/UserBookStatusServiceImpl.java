package com.annie.bibliobrowse_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.annie.bibliobrowse_api.composite_key.UserBookId;
import com.annie.bibliobrowse_api.dto.UserBookStatusDTO;
import com.annie.bibliobrowse_api.entity.Book;
import com.annie.bibliobrowse_api.entity.User;
import com.annie.bibliobrowse_api.entity.UserBookStatus;
import com.annie.bibliobrowse_api.repository.BookRepository;
import com.annie.bibliobrowse_api.repository.UserBookStatusRepository;
import com.annie.bibliobrowse_api.repository.UserRepository;
import com.annie.bibliobrowse_api.type.BookStatus;

@Service
public class UserBookStatusServiceImpl implements UserBookStatusService {
  @Autowired
  private UserBookStatusRepository userBookStatusRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BookRepository bookRepository;

  @Override
  public UserBookStatusDTO getById(UserBookId id) {
    UserBookStatus ubs = userBookStatusRepository.findById(id).orElseThrow(() -> new RuntimeException("User book status not found"));
    UserBookStatusDTO ubsDTO = new UserBookStatusDTO(ubs.getUser().getId(), ubs.getBook().getId(), ubs.getStatus());
    return ubsDTO;
  }

  @Override
  public UserBookStatusDTO createUpdateUserBookStatus(Long userId, Long bookId, BookStatus status) {
    // find user and book by their ids
    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
    BookStatus finalStatus = status != null ? status : BookStatus.Want_to_read;
    
    // Create new UserBookStatus object
    UserBookStatus newUserBookStatus = new UserBookStatus();
    newUserBookStatus.setId(new UserBookId(userId, bookId));
    newUserBookStatus.setUser(user);
    newUserBookStatus.setBook(book);
    newUserBookStatus.setStatus(finalStatus);
    userBookStatusRepository.save(newUserBookStatus);
    UserBookStatusDTO ubsDTO = new UserBookStatusDTO(userId, bookId, finalStatus);
    return ubsDTO;
  }

  @Override
  public String deleteUserBookStatus(UserBookId id) {
    if (userBookStatusRepository.existsById(id)) {
      userBookStatusRepository.deleteById(id);
      return "Book status of book id " + id.getBookId() + " from user id " + id.getUserId() + " is deleted Successfully";
    }
    return "Failed to delete book status of book id " + id.getBookId() + " from user id " + id.getUserId();
  }

}

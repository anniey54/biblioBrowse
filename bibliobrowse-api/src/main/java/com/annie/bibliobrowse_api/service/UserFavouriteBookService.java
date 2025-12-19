package com.annie.bibliobrowse_api.service;

import java.util.List;

import com.annie.bibliobrowse_api.composite_key.UserBookId;
import com.annie.bibliobrowse_api.dto.UserBookDTO;
import com.annie.bibliobrowse_api.entity.Book;

public interface UserFavouriteBookService {
  List<Book> getBooksByUserId(Long userId);
  UserBookDTO createFavouriteBook(Long userId, Long bookId);
  String deleteFavouriteBook(UserBookId id);
}

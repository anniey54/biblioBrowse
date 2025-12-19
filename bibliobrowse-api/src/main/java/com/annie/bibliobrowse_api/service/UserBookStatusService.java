package com.annie.bibliobrowse_api.service;

import com.annie.bibliobrowse_api.composite_key.UserBookId;
import com.annie.bibliobrowse_api.dto.UserBookStatusDTO;
import com.annie.bibliobrowse_api.type.BookStatus;

public interface UserBookStatusService {

  UserBookStatusDTO getById(UserBookId id);
  UserBookStatusDTO createUpdateUserBookStatus(Long userId, Long bookId, BookStatus status);
  String deleteUserBookStatus(UserBookId id);
}

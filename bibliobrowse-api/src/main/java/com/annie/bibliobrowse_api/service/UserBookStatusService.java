package com.annie.bibliobrowse_api.service;

import com.annie.bibliobrowse_api.composite_key.UserBookId;
import com.annie.bibliobrowse_api.dto.UserBookStatusDTO;

public interface UserBookStatusService {

  UserBookStatusDTO getById(UserBookId id);
  UserBookStatusDTO createUpdateUserBookStatus(Long userId, Long bookId, String status);
  String deleteUserBookStatus(UserBookId id);
}

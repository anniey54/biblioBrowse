package com.annie.bibliobrowse_api.service;

import java.util.List;

import com.annie.bibliobrowse_api.composite_key.CollectionBookId;
import com.annie.bibliobrowse_api.dto.CollectionBookDTO;
import com.annie.bibliobrowse_api.entity.Book;

public interface CollectionBookService {
  List<Book> getBooksByCollectionId(Long collectionId);
  CollectionBookDTO createCollectionBook(Long collectionId, Long bookId);
  String deleteCollectionBook(CollectionBookId id);
}

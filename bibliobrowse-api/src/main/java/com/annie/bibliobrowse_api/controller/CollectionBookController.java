package com.annie.bibliobrowse_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.annie.bibliobrowse_api.composite_key.CollectionBookId;
import com.annie.bibliobrowse_api.dto.CollectionBookDTO;
import com.annie.bibliobrowse_api.entity.Book;
import com.annie.bibliobrowse_api.service.CollectionBookService;

@RestController
@CrossOrigin(maxAge = 3360)
public class CollectionBookController {
  @Autowired
  private CollectionBookService collectionBookService;

  @GetMapping("/api/collection-book/collection/{collectionId}")
  public ResponseEntity<List<Book>> getAllCollectionBooks(@PathVariable("collectionId") Long collectionId) {
      return ResponseEntity.ok(collectionBookService.getBooksByCollectionId(collectionId));
  }
  
  @PostMapping("/api/collection-book")
  public ResponseEntity<CollectionBookDTO> createCollectionBook(@RequestBody CollectionBookDTO requestDTO) {
    return ResponseEntity.ok(collectionBookService.createCollectionBook(requestDTO.getCollectionId(), requestDTO.getBookId()));
  }

  @DeleteMapping("/api/collection-book/collection/{collectionId}/book/{bookId}")
  public ResponseEntity<String> deleteCollectionBook(@PathVariable("collectionId") Long collectionId, @PathVariable("bookId") Long bookId) {
    CollectionBookId id = new CollectionBookId(collectionId, bookId);
    return ResponseEntity.ok(collectionBookService.deleteCollectionBook(id));
  }
}

package com.annie.bibliobrowse_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.annie.bibliobrowse_api.entity.Collection;
import com.annie.bibliobrowse_api.service.CollectionService;

@RestController
@CrossOrigin(maxAge = 3360)
public class CollectionController {
  @Autowired
  private CollectionService collectionService;

  @GetMapping("/api/collections")
  public ResponseEntity<List<Collection>> getAllCollections() {
      return ResponseEntity.ok(collectionService.getAllCollections());
  }

  @GetMapping("/api/collections/{id}")
  public ResponseEntity<Collection> getCollection(@PathVariable("id") Long id) {
      return ResponseEntity.ok(collectionService.getCollection(id));
  }
  
  @PostMapping("/api/collections")
  public ResponseEntity<Collection> createCollection(@RequestBody Collection collection) {
      return ResponseEntity.ok(collectionService.createCollection(collection));
  }

  @PutMapping("/api/collections")
  public ResponseEntity<Collection> updateCollection(@RequestBody Collection collection) {
    return ResponseEntity.ok(collectionService.updateCollection(collection));
  }

  @DeleteMapping("/api/collections/{id}")
  public ResponseEntity<String> deleteCollection(@PathVariable("id") Long id) {
    Collection collectionObject = collectionService.getCollection(id);
    String resultMessage = null;

    if (collectionObject != null) {
      resultMessage = collectionService.deleteCollection(collectionObject);
    }
    return ResponseEntity.ok(resultMessage);
  }
}

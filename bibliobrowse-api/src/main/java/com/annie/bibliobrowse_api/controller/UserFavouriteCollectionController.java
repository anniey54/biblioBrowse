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

import com.annie.bibliobrowse_api.composite_key.UserCollectionId;
import com.annie.bibliobrowse_api.dto.UserCollectionDTO;
import com.annie.bibliobrowse_api.entity.Collection;
import com.annie.bibliobrowse_api.service.UserFavouriteCollectionService;

@RestController
@CrossOrigin(maxAge = 3360)
public class UserFavouriteCollectionController {
  @Autowired
  private UserFavouriteCollectionService userFavouriteCollectionService;

  @GetMapping("/api/favourite-collection/user/{userId}")
  public ResponseEntity<List<Collection>> getAllFavouriteCollections(@PathVariable("userId") Long userId) {
      return ResponseEntity.ok(userFavouriteCollectionService.getCollectionsByUserId(userId));
  }
  
  @PostMapping("/api/favourite-collection")
  public ResponseEntity<UserCollectionDTO> createFavouriteCollection(@RequestBody UserCollectionDTO requestDTO) {
    return ResponseEntity.ok(userFavouriteCollectionService.createFavouriteCollection(requestDTO.getUserId(), requestDTO.getCollectionId()));
  }

  @DeleteMapping("/api/favourite-collection/user/{userId}/collection/{collectionId}")
  public ResponseEntity<String> deleteFavouriteCollection(@PathVariable("userId") Long userId, @PathVariable("collectionId") Long collectionId) {
    UserCollectionId id = new UserCollectionId(userId, collectionId);
    return ResponseEntity.ok(userFavouriteCollectionService.deleteFavouriteCollection(id));
  }
}

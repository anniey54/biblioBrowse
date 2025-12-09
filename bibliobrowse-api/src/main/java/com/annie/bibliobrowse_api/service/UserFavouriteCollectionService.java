package com.annie.bibliobrowse_api.service;

import java.util.List;

import com.annie.bibliobrowse_api.composite_key.UserCollectionId;
import com.annie.bibliobrowse_api.dto.UserCollectionDTO;
import com.annie.bibliobrowse_api.entity.Collection;

public interface UserFavouriteCollectionService {
  List<Collection> getCollectionsByUserId(Long userId);
  UserCollectionDTO createFavouriteCollection(Long userId, Long collectionId);
  String deleteFavouriteCollection(UserCollectionId id);
}

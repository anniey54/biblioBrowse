package com.annie.bibliobrowse_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.annie.bibliobrowse_api.composite_key.UserCollectionId;
import com.annie.bibliobrowse_api.dto.UserCollectionDTO;
import com.annie.bibliobrowse_api.entity.User;
import com.annie.bibliobrowse_api.entity.Collection;
import com.annie.bibliobrowse_api.entity.UserFavouriteCollection;
import com.annie.bibliobrowse_api.repository.CollectionRepository;
import com.annie.bibliobrowse_api.repository.UserFavouriteCollectionRepository;
import com.annie.bibliobrowse_api.repository.UserRepository;

@Service
public class UserFavouriteCollectionServiceImpl implements UserFavouriteCollectionService {
  @Autowired
  private UserFavouriteCollectionRepository userFavouriteCollectionRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CollectionRepository collectionRepository;

  @Override
  public List<Collection> getCollectionsByUserId(Long userId) {
    List<Collection> collections = userFavouriteCollectionRepository.findFavouriteCollectionsByUserId(userId)
      .orElseThrow(() -> new RuntimeException("Favourite collections not found"));
    return collections;
  }

  @Override
  public UserCollectionDTO createFavouriteCollection(Long userId, Long collectionId) {
    // find user and collection by their ids
    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    Collection collection = collectionRepository.findById(collectionId).orElseThrow(() -> new RuntimeException("Collection not found"));
    
    // Create new UserFavouriteCollection object
    UserFavouriteCollection newUserFavouriteCollection = new UserFavouriteCollection();
    newUserFavouriteCollection.setId(new UserCollectionId(userId, collectionId));
    newUserFavouriteCollection.setUser(user);
    newUserFavouriteCollection.setCollection(collection);
    userFavouriteCollectionRepository.save(newUserFavouriteCollection);
    UserCollectionDTO ucDTO = new UserCollectionDTO(userId, collectionId);
    return ucDTO;
  }

  @Override
  public String deleteFavouriteCollection(UserCollectionId id) {
    if (userFavouriteCollectionRepository.existsById(id)) {
      userFavouriteCollectionRepository.deleteById(id);
      return "Favourite collection id " + id.getCollectionId() + " of user id " + id.getUserId() + " is deleted Successfully";
    }
    return "Failed to delete favourite collection id " + id.getCollectionId() + " of user id " + id.getUserId();
  }
  
}

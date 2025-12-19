package com.annie.bibliobrowse_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.annie.bibliobrowse_api.composite_key.UserCollectionId;
import com.annie.bibliobrowse_api.entity.Collection;
import com.annie.bibliobrowse_api.entity.UserFavouriteCollection;

@Repository
public interface UserFavouriteCollectionRepository extends CrudRepository<UserFavouriteCollection, UserCollectionId> {

  @Query("SELECT collection FROM Collection collection, UserFavouriteCollection userFavouriteCollection  WHERE userFavouriteCollection.user.id = :userId AND collection.id = userFavouriteCollection.collection.id")
  Optional<List<Collection>> findFavouriteCollectionsByUserId(Long userId);
}

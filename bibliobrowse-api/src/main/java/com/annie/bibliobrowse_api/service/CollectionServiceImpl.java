package com.annie.bibliobrowse_api.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.annie.bibliobrowse_api.entity.Collection;
import com.annie.bibliobrowse_api.repository.CollectionRepository;

@Service
public class CollectionServiceImpl implements CollectionService {
  @Autowired
	private CollectionRepository collectionRepository;

  @Override
  public List<Collection> getAllCollections() {
    return (List<Collection>) collectionRepository.findAll();
  }

  @Override
  public Collection getCollection(Long id) {
    return collectionRepository.findById(id).get();
  }

  @Override
  public List<Collection> searchCollections(String query) {
    List<Collection> collections = collectionRepository.findByTitleAndCreator(query.toLowerCase())
      .orElseThrow(() -> new RuntimeException("Collections are not found"));
    return collections;
  }

  @Override
  public Collection createCollection(Collection collection) {
    return collectionRepository.save(collection);
  }

  @Override
  public Collection updateCollection(Collection collection) {
    Date date = new Date();
    collection.setLastUpdatedAt(date);
    return collectionRepository.save(collection);
  }

  @Override
  public String deleteCollection(Collection collection) {
    collectionRepository.delete(collection);
		return "Collection with id " + collection.getId() + " is deleted Successfully";
  }

}

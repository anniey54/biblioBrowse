package com.annie.bibliobrowse_api.service;

import java.util.List;

import com.annie.bibliobrowse_api.entity.Collection;

public interface CollectionService {
  List<Collection> getAllCollections();
	Collection getCollection(Long id);
	Collection createCollection(Collection collection);
	Collection updateCollection(Collection collection);
	String deleteCollection(Collection collection);
  List<Collection> searchCollections(String query);
}

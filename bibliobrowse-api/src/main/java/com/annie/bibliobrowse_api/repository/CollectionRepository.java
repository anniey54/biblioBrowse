package com.annie.bibliobrowse_api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.annie.bibliobrowse_api.entity.Collection;

@Repository
public interface CollectionRepository extends CrudRepository<Collection, Long> {

}

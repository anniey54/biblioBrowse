package com.annie.bibliobrowse_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.annie.bibliobrowse_api.entity.Collection;

@Repository
public interface CollectionRepository extends CrudRepository<Collection, Long> {

  @Query("SELECT c FROM Collection c JOIN User u ON c.creator = u.id WHERE c.status = 'Public' AND (LOWER(c.title) LIKE %:text% OR LOWER(u.username) LIKE %:text%)")
  Optional<List<Collection>> findByTitleAndCreator(String text);
}

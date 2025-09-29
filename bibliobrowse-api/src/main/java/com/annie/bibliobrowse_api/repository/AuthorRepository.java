package com.annie.bibliobrowse_api.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.annie.bibliobrowse_api.domain.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

}

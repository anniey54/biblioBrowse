package com.annie.bibliobrowse_api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.annie.bibliobrowse_api.composite_key.UserBookId;
import com.annie.bibliobrowse_api.entity.UserBookStatus;

@Repository
public interface UserBookStatusRepository extends CrudRepository<UserBookStatus, UserBookId> {

}

package com.annie.bibliobrowse_api.repository;

import org.springframework.stereotype.Repository;

import com.annie.bibliobrowse_api.entity.User;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}

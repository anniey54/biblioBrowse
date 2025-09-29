package com.annie.bibliobrowse_api.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.annie.bibliobrowse_api.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}

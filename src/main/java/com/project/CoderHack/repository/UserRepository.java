package com.project.CoderHack.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.CoderHack.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

}

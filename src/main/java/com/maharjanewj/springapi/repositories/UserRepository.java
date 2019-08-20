package com.maharjanewj.springapi.repositories;

import com.maharjanewj.springapi.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);
}

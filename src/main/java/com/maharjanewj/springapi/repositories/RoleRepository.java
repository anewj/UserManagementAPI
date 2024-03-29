package com.maharjanewj.springapi.repositories;

import com.maharjanewj.springapi.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRole(String role);
}

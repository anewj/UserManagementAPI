package com.maharjanewj.springapi.repositories;

import com.maharjanewj.springapi.models.Privileges;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrivilegeRepository extends MongoRepository<Privileges,String> {
    Privileges findByPrivilege(String privilege);

}

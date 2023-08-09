package com.spring.learning.learning.repositorys;

import com.spring.learning.learning.entitys.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,String> {
    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}

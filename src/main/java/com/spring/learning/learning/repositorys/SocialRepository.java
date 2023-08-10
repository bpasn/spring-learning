package com.spring.learning.learning.repositorys;

import com.spring.learning.learning.entitys.EnSocial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialRepository extends CrudRepository<EnSocial,String> {
}

package com.spring.learning.learning.repositorys;

import com.spring.learning.learning.entitys.EnAddress;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<EnAddress, String> {
}

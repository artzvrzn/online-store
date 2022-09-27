package com.artzvrzn.store.auth2.dao.sql;

import com.artzvrzn.store.auth2.domain.User;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

}

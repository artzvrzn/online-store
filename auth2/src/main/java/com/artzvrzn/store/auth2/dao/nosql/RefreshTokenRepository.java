package com.artzvrzn.store.auth2.dao.nosql;

import com.artzvrzn.store.auth2.domain.RefreshToken;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends KeyValueRepository<RefreshToken, String> {

}

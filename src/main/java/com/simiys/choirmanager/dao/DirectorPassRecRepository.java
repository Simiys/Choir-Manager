package com.simiys.choirmanager.dao;

import com.simiys.choirmanager.model.tokens.DirectorPasswordRecoveryToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DirectorPassRecRepository extends CrudRepository<DirectorPasswordRecoveryToken, Long> {
    Optional<DirectorPasswordRecoveryToken> findByToken (String token);
}

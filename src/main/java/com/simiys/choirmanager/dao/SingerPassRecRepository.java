package com.simiys.choirmanager.dao;

import com.simiys.choirmanager.model.tokens.SingerPasswordRecoveryToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SingerPassRecRepository extends CrudRepository<SingerPasswordRecoveryToken, Long> {
    Optional<SingerPasswordRecoveryToken> findByToken (String token);
}

package com.simiys.choirmanager.dao;

import com.simiys.choirmanager.model.tables.tokens.SingerVerificationToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SingerTokenRepository extends CrudRepository<SingerVerificationToken, Long> {
    Optional<SingerVerificationToken> findByToken(String token);

}

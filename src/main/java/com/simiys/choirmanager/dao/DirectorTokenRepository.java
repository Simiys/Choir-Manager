package com.simiys.choirmanager.dao;

import com.simiys.choirmanager.model.tokens.DirectorVerificationToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DirectorTokenRepository extends CrudRepository<DirectorVerificationToken, Long> {
    Optional<DirectorVerificationToken> findByToken(String token);
}

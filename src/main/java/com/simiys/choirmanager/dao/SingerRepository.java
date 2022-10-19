package com.simiys.choirmanager.dao;

import com.simiys.choirmanager.model.tables.Singer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@Qualifier("SingerRepository")
public interface SingerRepository extends CrudRepository<Singer, Long> {
    Optional<Singer> findByEmail(String email);
}

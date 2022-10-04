package com.simiys.choirmanager.dao;

import com.simiys.choirmanager.model.ChoirDirector;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@Qualifier("DirectorRepository")
public interface DirectorRepository extends CrudRepository<ChoirDirector, Long> {
    Optional<ChoirDirector> findByEmail(String email);
    Optional<ChoirDirector> findById (long id);
}

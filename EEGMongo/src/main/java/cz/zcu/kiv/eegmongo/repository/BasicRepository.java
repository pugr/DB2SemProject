package cz.zcu.kiv.eegmongo.repository;

import cz.zcu.kiv.eegmongo.crossstore.domain.Scenario;
import cz.zcu.kiv.eegmongo.crossstore.domain.SubjectDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 23.5.12
 */
public interface BasicRepository<T> extends MongoRepository<T, String> {

    @Query("{ 'userId' : ?0 }")
    public List<T> findByTheUserId(String userId);



}

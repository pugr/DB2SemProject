package cz.zcu.kiv.eegmongo.repository;

import cz.zcu.kiv.eegmongo.crossstore.domain.p300.P300Document;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 16.5.12
 */
public interface P300DocumentRepository extends BasicRepository<P300Document> {
}

package cz.zcu.kiv.eegmongo.repository;

import cz.zcu.kiv.eegmongo.crossstore.domain.generic.GenericDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 17.5.12
 */
public interface GenericDocumentRepository extends BasicRepository<GenericDocument> {
}

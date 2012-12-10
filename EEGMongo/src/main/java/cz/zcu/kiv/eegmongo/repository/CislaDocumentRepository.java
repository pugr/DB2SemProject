package cz.zcu.kiv.eegmongo.repository;

import cz.zcu.kiv.eegmongo.crossstore.domain.cisla.CislaDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 16.5.12
 */
public interface CislaDocumentRepository extends BasicRepository<CislaDocument> {
}

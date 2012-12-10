package cz.zcu.kiv.eegmongo.repository;

import cz.zcu.kiv.eegmongo.crossstore.domain.SubjectDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 15.5.12
 */
public interface SubjectDocumentRepository extends BasicRepository<SubjectDocument> {
}
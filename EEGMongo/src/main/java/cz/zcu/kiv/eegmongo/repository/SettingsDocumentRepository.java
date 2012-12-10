package cz.zcu.kiv.eegmongo.repository;

import cz.zcu.kiv.eegmongo.crossstore.domain.settings.SettingsDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 16.5.12
 */
public interface SettingsDocumentRepository extends BasicRepository<SettingsDocument> {
}

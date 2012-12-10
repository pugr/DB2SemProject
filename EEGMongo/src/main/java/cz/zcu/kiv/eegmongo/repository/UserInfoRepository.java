package cz.zcu.kiv.eegmongo.repository;

import cz.zcu.kiv.eegmongo.crossstore.domain.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 15.5.12
 */
public interface UserInfoRepository extends MongoRepository<UserInfo, Long> {
}

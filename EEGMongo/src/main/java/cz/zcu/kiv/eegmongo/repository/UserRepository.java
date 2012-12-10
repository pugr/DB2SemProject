package cz.zcu.kiv.eegmongo.repository;

import cz.zcu.kiv.eegmongo.crossstore.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 15.5.12
 */
public interface UserRepository extends JpaRepository<User, Long> {

}

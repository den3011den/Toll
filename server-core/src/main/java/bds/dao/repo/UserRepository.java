package bds.dao.repo;

import org.springframework.data.repository.CrudRepository;
import bds.dao.UserRecord;

public interface UserRepository extends CrudRepository<UserRecord, Integer> {
}

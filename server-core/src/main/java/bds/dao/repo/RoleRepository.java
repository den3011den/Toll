package bds.dao.repo;

import org.springframework.data.repository.CrudRepository;
import bds.dao.RoleRecord;

public interface RoleRepository extends CrudRepository<RoleRecord, Integer> {
}

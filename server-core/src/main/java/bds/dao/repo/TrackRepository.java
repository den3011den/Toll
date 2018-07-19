package bds.dao.repo;

import bds.dao.TrackPoint;
import org.springframework.data.repository.CrudRepository;


public interface TrackRepository extends CrudRepository<TrackPoint, Integer> {
}

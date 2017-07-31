package zup.mateus.cruz.xyinc.repository;

import zup.mateus.cruz.xyinc.model.PointOfInterest;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.UUID;

public interface PointOfInterestRepository extends MongoRepository<PointOfInterest, UUID> {

    public List<PointOfInterest> findByName(String name);

}

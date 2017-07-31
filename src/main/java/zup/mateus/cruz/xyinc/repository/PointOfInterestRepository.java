package zup.mateus.cruz.xyinc.repository;

import zup.mateus.cruz.xyinc.model.PointOfInterest;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.UUID;

public interface PointOfInterestRepository extends MongoRepository<PointOfInterest, UUID> {

    public PointOfInterest findByName(String name);
    public List<PointOfInterest> findByCoordx(int coordx);
    public List<PointOfInterest> findByCoordy(int coordy);

}

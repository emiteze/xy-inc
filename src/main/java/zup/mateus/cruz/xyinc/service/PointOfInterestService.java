package zup.mateus.cruz.xyinc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zup.mateus.cruz.xyinc.repository.PointOfInterestRepository;
import zup.mateus.cruz.xyinc.model.PointOfInterest;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PointOfInterestService {

    @Autowired
    private PointOfInterestRepository repository;

    public List<PointOfInterest> getPoints(){
        return repository.findAll();
    }

    public List<PointOfInterest> getPointsNearby(PointOfInterest userLocation, double maxDistance){
        List<PointOfInterest> nearbyPoints = this.getPoints().stream().filter(point -> userLocation.distanceBetweenPoints(point) <= maxDistance).collect(Collectors.toList());
        return nearbyPoints;
    }

    public PointOfInterest findPointById(UUID id){
        return repository.findOne(id);
    }

    public List<PointOfInterest> findPointsByName(String name){
        return repository.findByName(name);
    }

    public void deleteByName(String name){
        List<PointOfInterest> pointsToBeRemoved = findPointsByName(name);
        repository.delete(pointsToBeRemoved);
    }

    public void deleteObject(PointOfInterest poi) { repository.delete(poi); }

    public void deleteById(UUID id){
        repository.delete(id);
    }

    public void savePoint(PointOfInterest poi){
        repository.save(poi);
    }

}

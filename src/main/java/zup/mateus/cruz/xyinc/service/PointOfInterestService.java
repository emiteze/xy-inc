package zup.mateus.cruz.xyinc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zup.mateus.cruz.xyinc.repository.PointOfInterestRepository;
import zup.mateus.cruz.xyinc.model.PointOfInterest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PointOfInterestService {

    @Autowired
    private PointOfInterestRepository repository;

    public List<PointOfInterest> getPoints(){
        return repository.findAll();
    }

    public List<PointOfInterest> getPointsNearby(PointOfInterest userLocation, double maxDistance){
        List<PointOfInterest> nearbyPoints = new ArrayList<>();
        for (PointOfInterest poi : this.getPoints()) {
            if(userLocation.distanceBetweenPoints(poi) <= maxDistance) nearbyPoints.add(poi);
        }
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
        for(PointOfInterest poi : pointsToBeRemoved){
            repository.delete(poi.getId());
        }
    }

    public void deleteObject(PointOfInterest poi) { repository.delete(poi); }

    public void deleteById(UUID id){
        repository.delete(id);
    }

    public void savePoint(PointOfInterest poi){
        repository.save(poi);
    }

    public void updatePoint(PointOfInterest poi){ repository.save(poi); }

}

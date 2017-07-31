package zup.mateus.cruz.xyinc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zup.mateus.cruz.xyinc.repository.PointOfInterestRepository;
import zup.mateus.cruz.xyinc.model.PointOfInterest;
import java.util.ArrayList;
import java.util.List;

@Service
public class PointOfInterestService {

    @Autowired
    private PointOfInterestRepository repository;

    public List<PointOfInterest> getPoints(){
        return repository.findAll();
    }

    public List<PointOfInterest> getPointsNearby(PointOfInterest userLocation, double maxDistance){
        List<PointOfInterest> nearbyPoints = new ArrayList<>();
        for (PointOfInterest poi : repository.findAll()) {
            if(userLocation.distanceBetweenPoints(poi) <= maxDistance) nearbyPoints.add(poi);
        }
        return nearbyPoints;
    }

    public List<PointOfInterest> findPointByName(String name){
        return repository.findByName(name);
    }

    public void savePoint(PointOfInterest poi){
        repository.save(poi);
    }

}

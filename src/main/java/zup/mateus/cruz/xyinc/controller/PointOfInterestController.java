package zup.mateus.cruz.xyinc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import zup.mateus.cruz.xyinc.model.PointOfInterest;
import zup.mateus.cruz.xyinc.service.PointOfInterestService;

@RestController
public class PointOfInterestController {

    @Autowired
    private PointOfInterestService poiService;

    @RequestMapping(value = "/get-points", method = RequestMethod.GET)
    public List<PointOfInterest> getPoints(){
        return poiService.getPoints();
    }

    @RequestMapping(value = "/get-points-nearby", method = RequestMethod.GET)
    public List<PointOfInterest> getPointsNearby(@RequestParam("coordx") int coordx, @RequestParam("coordy") int coordy, @RequestParam("maxDistance") double maxDistance){
        PointOfInterest userLocation = new PointOfInterest("User Location", coordx, coordy);
        return poiService.getPointsNearby(userLocation, maxDistance);
    }

    @RequestMapping(value = "/save-point", method = RequestMethod.POST)
    public List<PointOfInterest> savePoint(@RequestBody PointOfInterest poi){
        poiService.savePoint(poi);
        return poiService.getPoints();
    }

    @RequestMapping(value = "/save-point", method = RequestMethod.GET)
    public List<PointOfInterest> savePoint(@RequestParam("name") String name, @RequestParam("coordx") int coordx, @RequestParam("coordy") int coordy){
        poiService.savePoint(new PointOfInterest(name, coordx, coordy));
        return poiService.getPoints();
    }

}

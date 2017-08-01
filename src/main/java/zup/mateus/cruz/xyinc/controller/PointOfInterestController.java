package zup.mateus.cruz.xyinc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zup.mateus.cruz.xyinc.model.PointOfInterest;
import zup.mateus.cruz.xyinc.service.PointOfInterestService;

@RestController
public class PointOfInterestController {

    @Autowired
    private PointOfInterestService poiService;

    @RequestMapping(value = "/get-points", method = RequestMethod.GET)
    public ResponseEntity<?> getPoints(){
        try {
            return ResponseEntity.ok(poiService.getPoints());
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/get-points-nearby", method = RequestMethod.GET)
    public ResponseEntity<?> getPointsNearby(@RequestParam("coordx") int coordx, @RequestParam("coordy") int coordy, @RequestParam("maxDistance") double maxDistance){
        try {
            PointOfInterest userLocation = new PointOfInterest("User Location", coordx, coordy);
            return ResponseEntity.ok(poiService.getPointsNearby(userLocation, maxDistance));
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/find-points", method = RequestMethod.GET)
    public ResponseEntity<?> getPointsNearby(@RequestParam("name") String name){
        try {
            return ResponseEntity.ok(poiService.findPointByName(name));
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/save-point", method = RequestMethod.POST)
    public ResponseEntity<?> savePoint(@RequestBody PointOfInterest poi){
        try {
            poiService.savePoint(poi);
            return ResponseEntity.ok(poiService.getPoints());
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    /*@RequestMapping(value = "/save-point", method = RequestMethod.POST)
    public List<PointOfInterest> savePoint(@RequestParam("name") String name, @RequestParam("coordx") int coordx, @RequestParam("coordy") int coordy){
        poiService.savePoint(new PointOfInterest(name, coordx, coordy));
        return poiService.getPoints();
    }*/

}

package zup.mateus.cruz.xyinc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            return new ResponseEntity<>(poiService.getPoints(), HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/get-points-nearby", method = RequestMethod.GET)
    public ResponseEntity<?> getPointsNearby(@RequestParam("coordx") int coordx, @RequestParam("coordy") int coordy, @RequestParam("maxDistance") double maxDistance){
        try {
            PointOfInterest userLocation = new PointOfInterest("User Location", coordx, coordy);
            return new ResponseEntity<>(poiService.getPointsNearby(userLocation, maxDistance), HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/find-points", method = RequestMethod.GET)
    public ResponseEntity<?> getPointsNearby(@RequestParam("name") String name){
        try {
            return new ResponseEntity<>(poiService.findPointByName(name), HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/save-point", method = RequestMethod.POST)
    public ResponseEntity<?> savePoint(@RequestBody PointOfInterest poi){
        try {
            poiService.savePoint(poi);
            return new ResponseEntity<>(poiService.getPoints(), HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*@RequestMapping(value = "/save-point", method = RequestMethod.POST)
    public List<PointOfInterest> savePoint(@RequestParam("name") String name, @RequestParam("coordx") int coordx, @RequestParam("coordy") int coordy){
        poiService.savePoint(new PointOfInterest(name, coordx, coordy));
        return poiService.getPoints();
    }*/

}

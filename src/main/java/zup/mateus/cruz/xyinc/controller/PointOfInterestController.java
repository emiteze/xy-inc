package zup.mateus.cruz.xyinc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zup.mateus.cruz.xyinc.model.PointOfInterest;
import zup.mateus.cruz.xyinc.service.PointOfInterestService;
import java.util.UUID;

@RestController
public class PointOfInterestController {

    @Autowired
    private PointOfInterestService poiService;

    @RequestMapping(value = "/get-point", method = RequestMethod.GET, params = "id")
    public ResponseEntity<?> findPoint(@RequestParam("id") UUID id){
        try {
            return ResponseEntity.ok(poiService.findPointById(id));
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/get-point", method = RequestMethod.GET, params = "name")
    public ResponseEntity<?> findPoint(@RequestParam("name") String name){
        try {
            return ResponseEntity.ok(poiService.findPointsByName(name));
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

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

    @RequestMapping(value = "/save-point", method = RequestMethod.POST)
    public ResponseEntity<?> savePoint(@RequestBody PointOfInterest poi){
        try {
            poiService.savePoint(poi);
            return ResponseEntity.ok(poiService.getPoints());
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/delete-point", method = RequestMethod.DELETE, params = "id")
    public ResponseEntity<?> deletePoint(@RequestParam("id") UUID id){
        try {
            poiService.deleteById(id);
            return ResponseEntity.ok(poiService.getPoints());
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/delete-point", method = RequestMethod.DELETE, params = "name")
    public ResponseEntity<?> deletePoint(@RequestParam("name") String name){
        try {
            poiService.deleteByName(name);
            return ResponseEntity.ok(poiService.getPoints());
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/delete-point", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePoint(@RequestBody PointOfInterest poi){
        try {
            poiService.deleteObject(poi);
            return ResponseEntity.ok(poiService.getPoints());
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/update-point", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePoint(@RequestBody PointOfInterest poi){
        try {
            poiService.updatePoint(poi);
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

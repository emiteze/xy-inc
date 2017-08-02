package zup.mateus.cruz.xyinc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import zup.mateus.cruz.xyinc.model.PointOfInterest;
import zup.mateus.cruz.xyinc.service.PointOfInterestService;
import java.util.UUID;

@RestController
public class PointOfInterestController {

    @Autowired
    private PointOfInterestService poiService;

    @RequestMapping(value = "/get-point", method = RequestMethod.GET)
    public ResponseEntity<?> findPoint(@RequestParam(value = "id", required = false) UUID id, @RequestParam(value = "name", required = false) String name){
        try {
            if(id != null){
                return ResponseEntity.ok(poiService.findPointById(id));
            } else if (!StringUtils.isEmpty(name)){
                return ResponseEntity.ok(poiService.findPointsByName(name));
            }
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
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

    @RequestMapping(value = "/delete-point", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePoint(@RequestParam(value = "id", required = false) UUID id, @RequestParam(value = "name", required = false) String name, @RequestBody(required = false) PointOfInterest poi){
        try {
            if(id != null){
                poiService.deleteById(id);
                return ResponseEntity.ok(poiService.getPoints());
            } else if(!StringUtils.isEmpty(name)){
                poiService.deleteByName(name);
                return ResponseEntity.ok(poiService.getPoints());
            } else if(poi != null){
                poiService.deleteObject(poi);
                return ResponseEntity.ok(poiService.getPoints());
            }
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
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

package zup.mateus.cruz.xyinc.model;

import org.junit.Assert;
import org.junit.Test;

public class PointOfInterestTest {

    @Test
    public void distanceBetweenPoints() throws Exception {
        PointOfInterest pointOne = new PointOfInterest("Point1", 10, 10);
        PointOfInterest pointTwo = new PointOfInterest("Point2", 20, 20);
        double distance = pointOne.distanceBetweenPoints(pointTwo);
        double expectedDistance = 14.14;
        Assert.assertEquals(expectedDistance, distance, 0.01);
    }

}
package zup.mateus.cruz.xyinc.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import zup.mateus.cruz.xyinc.model.PointOfInterest;
import zup.mateus.cruz.xyinc.repository.PointOfInterestRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PointOfInterestServiceTest {

    @Mock
    private PointOfInterestRepository repository;

    @InjectMocks
    private PointOfInterestService service;

    @Test
    public void testGetPointsNearbyRetrievingPoints() throws Exception {
        List<PointOfInterest> mockedList = populateMockList();
        PointOfInterest userLocation = new PointOfInterest("User Location", 15,15);
        when(service.getPoints()).thenReturn(mockedList);
        List<PointOfInterest> returnedList = service.getPointsNearby(userLocation, 10);
        List<PointOfInterest> expectedList = new ArrayList<>(Arrays.asList(new PointOfInterest("ponto1", 10, 10), new PointOfInterest("ponto2", 20, 20)));
        for(int i = 0; i < returnedList.size(); i++){
            PointOfInterest returnedObject = returnedList.get(0);
            PointOfInterest expectedObject = expectedList.get(0);
            Assert.assertEquals(expectedObject.getName(), returnedObject.getName());
            Assert.assertEquals(expectedObject.getCoordx(), returnedObject.getCoordx());
            Assert.assertEquals(expectedObject.getCoordy(), returnedObject.getCoordy());
        }
    }

    @Test
    public void testGetPointsNearbyNotRetrievingPoints() throws Exception {
        List<PointOfInterest> mockedList = populateMockList();
        PointOfInterest userLocation = new PointOfInterest("User Location", 15,15);
        when(service.getPoints()).thenReturn(mockedList);
        List<PointOfInterest> returnedList = service.getPointsNearby(userLocation, 1);
        List<PointOfInterest> expectedList = new ArrayList<>();
        Assert.assertEquals(returnedList, expectedList);
    }

    public List<PointOfInterest> populateMockList(){
        List<PointOfInterest> mockedList = new ArrayList<>();
        mockedList.addAll(Arrays.asList(new PointOfInterest("ponto1", 10, 10), new PointOfInterest("ponto2", 20, 20),new PointOfInterest("ponto3", 30, 30)));
        return mockedList;
    }

}
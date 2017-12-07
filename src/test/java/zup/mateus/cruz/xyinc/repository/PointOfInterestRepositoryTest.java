package zup.mateus.cruz.xyinc.repository;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import zup.mateus.cruz.xyinc.model.PointOfInterest;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PointOfInterestRepositoryTest {

    @Autowired
    private PointOfInterestRepository repository;

    private List<PointOfInterest> pointsInRepo = new ArrayList<>();
    private static List<PointOfInterest> testList = new ArrayList<>();

    @Before
    public void deleteDB() {
        pointsInRepo = repository.findAll();
        deleteInRepoEachPointInList(testList);
    }

    @After
    public void restoreDB() {
        deleteInRepoEachPointInList(testList);
        saveInRepoEachPointInList(pointsInRepo);
    }

    private void saveInRepoEachPointInList(List<PointOfInterest> listOfPoints){
        listOfPoints.forEach(point -> {
            repository.save(point);
        });
    }

    private void deleteInRepoEachPointInList(List<PointOfInterest> listOfPoints){
        listOfPoints.forEach(point -> {
            repository.delete(point);
        });
    }

    @Test
    public void testFindAllPopulatedDB() throws Exception {

        List<PointOfInterest> backup = new ArrayList<>();
        backup.addAll(repository.findAll());
        repository.deleteAll();

        PointOfInterest pointOne = new PointOfInterest("point1", 10, 10);
        PointOfInterest pointTwo = new PointOfInterest("point2", 20, 20);
        testList.add(repository.save(pointOne));
        testList.add(repository.save(pointTwo));
        int expectedSize = 2;
        int returnedSize = repository.findAll().size();
        Assert.assertEquals(expectedSize, returnedSize);

        saveInRepoEachPointInList(backup);

    }

    @Test
    public void testFindByNameDontReturnObject() throws Exception {
        PointOfInterest pointOne = new PointOfInterest("point1", 10, 10);
        testList.add(repository.save(pointOne));
        List<PointOfInterest> returnedList = repository.findByName("point2");
        int expectedSize = 0;
        int returnedSize = returnedList.size();
        Assert.assertEquals(expectedSize, returnedSize);
    }

    @Test
    public void testFindByNameReturnsOneObject() throws Exception {
        PointOfInterest pointOne = new PointOfInterest("point1", 10, 10);
        testList.add(repository.save(pointOne));
        List<PointOfInterest> returnedList = repository.findByName("point1");
        int expectedSize = 1;
        int returnedSize = returnedList.size();
        String expectedName = "point1";
        Assert.assertEquals(expectedSize, returnedSize);
        Assert.assertEquals(expectedName, returnedList.get(0).getName());
    }

    @Test
    public void testFindByNameReturnsListOfObject() throws Exception {
        PointOfInterest pointOne = new PointOfInterest("point1", 10, 10);
        PointOfInterest pointTwo = new PointOfInterest("point1", 20, 20);
        PointOfInterest pointThree = new PointOfInterest("point3", 30, 30);
        testList.add(repository.save(pointOne));
        testList.add(repository.save(pointTwo));
        testList.add(repository.save(pointThree));
        List<PointOfInterest> returnedList = repository.findByName("point1");
        int expectedSize = 2;
        int returnedSize = returnedList.size();
        String expectedName = "point1";
        Assert.assertEquals(expectedSize, returnedSize);
        for(int i = 0; i < returnedList.size(); i++){
            Assert.assertEquals(expectedName, returnedList.get(i).getName());
        }
    }

    @Test
    public void testDeleteByNonExistingId() throws Exception {

        List<PointOfInterest> backup = new ArrayList<>();
        backup.addAll(repository.findAll());
        repository.deleteAll();

        PointOfInterest pointOne = new PointOfInterest("point1", 10, 10);
        PointOfInterest pointTwo = new PointOfInterest("point2", 10, 10);
        testList.add(repository.save(pointOne));
        testList.add(repository.save(pointTwo));
        UUID idToBeRemoved = UUID.randomUUID();
        repository.delete(idToBeRemoved);
        int expectedListSize = 2;
        int returnedListSize = repository.findAll().size();
        Assert.assertEquals(expectedListSize, returnedListSize);
        Assert.assertEquals(null, repository.findOne(idToBeRemoved));

        saveInRepoEachPointInList(backup);

    }

    @Test
    public void testDeleteByExistingId() throws Exception {

        List<PointOfInterest> backup = new ArrayList<>();
        backup.addAll(repository.findAll());
        repository.deleteAll();

        PointOfInterest pointOne = new PointOfInterest("point1", 10, 10);
        PointOfInterest pointTwo = new PointOfInterest("point2", 10, 10);
        PointOfInterest pointThree = new PointOfInterest("point3", 30, 30);
        testList.add(repository.save(pointOne));
        testList.add(repository.save(pointTwo));
        testList.add(repository.save(pointThree));
        UUID idToBeRemoved = pointOne.getId();
        repository.delete(idToBeRemoved);
        int expectedListSize = 2;
        int returnedListSize = repository.findAll().size();
        Assert.assertEquals(expectedListSize, returnedListSize);
        Assert.assertEquals(null, repository.findOne(idToBeRemoved));

        saveInRepoEachPointInList(backup);

    }

    @Test
    public void savePoint() throws Exception {
        PointOfInterest expectedObject = new PointOfInterest("point1", 10, 10);
        testList.add(repository.save(expectedObject));
        PointOfInterest returnedObject = repository.findOne(expectedObject.getId());
        Assert.assertNotNull(returnedObject);
        Assert.assertEquals(expectedObject.getId(), returnedObject.getId());
        Assert.assertEquals(expectedObject.getName(), returnedObject.getName());
        Assert.assertEquals(expectedObject.getCoordx(), returnedObject.getCoordx());
        Assert.assertEquals(expectedObject.getCoordy(), returnedObject.getCoordy());
    }

    @Test(expected = ConstraintViolationException.class)
    public void savePointWithEmptyNameValue() throws Exception {
        PointOfInterest expectedObject = new PointOfInterest("", 10, 10);
        testList.add(repository.save(expectedObject));
    }

    @Test(expected = ConstraintViolationException.class)
    public void savePointWithNegativeXValue() throws Exception {
        PointOfInterest expectedObject = new PointOfInterest("point1", -1, 10);
        testList.add(repository.save(expectedObject));
    }

    @Test(expected = ConstraintViolationException.class)
    public void savePointWithNegativeYValue() throws Exception {
        PointOfInterest expectedObject = new PointOfInterest("point1", 10, -1);
        testList.add(repository.save(expectedObject));
    }

}
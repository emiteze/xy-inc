package zup.mateus.cruz.xyinc.repository;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import zup.mateus.cruz.xyinc.model.PointOfInterest;
import java.util.List;

import static org.junit.Assert.*;

public class PointOfInterestRepositoryTest {

    @Autowired
    private PointOfInterestRepository repository;

    List<PointOfInterest> listOfPOI;

    @BeforeClass
    public static void backupDB() {

    }

    @AfterClass
    public static void restoreDB(){

    }

    @Test
    public void testFindAll(){

    }

    @Test
    public void testFindByName(){

    }

    @Test
    public void testDeleteByName(){

    }

    @Test
    public void savePoint(){

    }

}
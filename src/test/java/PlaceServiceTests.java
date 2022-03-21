import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tselebrovskii.Application;
import tselebrovskii.entity.PlaceEntity;
import tselebrovskii.repository.PlaceRepository;
import tselebrovskii.service.PlaceService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
public class PlaceServiceTests {

    private static final int PLACES_COUNT = 5;

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    PlaceService placeService;

    @BeforeEach
    void initDB() {
        for (int i = 0; i < PLACES_COUNT; i++)
            placeRepository.save(PlaceEntity.builder()
                    .name("Place #" + i)
                    .photo(null)
                    .build());
    }

    @AfterEach
    void clearDB() {
        placeRepository.deleteAll();
    }

    @Test
    void loadComponent() {
        assertNotNull(placeService);
    }

    @Test
    void findAllTest() {
        assertEquals(PLACES_COUNT, placeService.findAll().size());
    }

}

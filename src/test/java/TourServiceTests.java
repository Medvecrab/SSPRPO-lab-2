import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tselebrovskii.Application;
import tselebrovskii.entity.GuideEntity;
import tselebrovskii.entity.PlaceEntity;
import tselebrovskii.entity.TourEntity;
import tselebrovskii.form.TourForm;
import tselebrovskii.repository.GuideRepository;
import tselebrovskii.repository.PlaceRepository;
import tselebrovskii.repository.TourRepository;
import tselebrovskii.service.TourService;

import java.math.BigInteger;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
public class TourServiceTests {

    @Autowired
    private TourService tourService;

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private GuideRepository guideRepository;

    private static final int TOUR_COUNT = 5;

    private static GuideEntity guideForTest;

    private static PlaceEntity placeForTest;

    private static TourEntity tourForTest;

    private void saveEntities() {
        guideForTest = GuideEntity.builder()
                .surname("TEST NAME")
                .name("TEST SURNAME")
                .workDays("")
                .salary(BigInteger.valueOf(0L))
                .build();
        placeForTest = PlaceEntity.builder()
                .name("TEST PLACE")
                .build();
        guideRepository.save(guideForTest);
        placeRepository.save(placeForTest);
    }

    @BeforeAll
    static void initTourForTest() {
        tourForTest = TourEntity.builder()
                .name("TEST TOUR")
                .guide(guideForTest)
                .place(placeForTest)
                .duration(new Time(0))
                .costs(BigInteger.valueOf(0L))
                .tourDays("TEST DAYS")
                .build();
    }

    @BeforeEach
    @Transactional
    void initDB() {
        saveEntities();
        for (int i = 0; i < TOUR_COUNT; i++) {
            tourRepository.save(TourEntity.builder()
                    .name("Name #" + i)
                    .costs(BigInteger.valueOf(i))
                    .duration(new Time(i))
                    .tourDays("TEST DAYS")
                    .guide(guideForTest)
                    .place(placeForTest)
                    .build());
        }

    }

    @AfterEach
    @Transactional
    void clearDB() {
        tourRepository.deleteAll();
        guideRepository.deleteAll();
        placeRepository.deleteAll();
    }

    @Test
    void loadComponent() {
        assertNotNull(tourService);
    }

    @Test
    @Transactional
    void findAllTest() {
        assertEquals(TOUR_COUNT, tourService.findAll().size());
    }

    @Test
    @Transactional
    void findByIdTest() {
        List<TourEntity> allTours = tourRepository.findAll();
        Optional<TourEntity> findEntity = tourRepository.findById(allTours.get(0).getId());
        assertTrue(findEntity.isPresent());
        assertEquals(allTours.get(0).getId(), findEntity.get().getId());
        assertEquals(allTours.get(0).getName(), findEntity.get().getName());
    }

    @Test
    @Transactional
    void saveTest() {
        TourForm tourForm = TourForm.builder()
                .name(tourForTest.getName())
                .tourDays(tourForTest.getTourDays())
                .description(tourForTest.getDescription())
                .costs(tourForTest.getCosts())
                .duration(tourForTest.getDuration().toString())
                .guideId(guideRepository.findAll().get(0).getId())
                .placeId(placeRepository.findAll().get(0).getId())
                .build();
        tourService.save(tourForm);
        List<TourEntity> allTours = tourRepository.findAll();
        assertNotEquals(TOUR_COUNT, allTours.size());
        assertTrue(allTours.stream().anyMatch(tour -> tour.getName().equals(tourForTest.getName())));
    }

    @Test
    @Transactional
    void updateTest() {
        List<TourEntity> allTours = tourRepository.findAll();
        TourEntity updatable = allTours.get(0);
        TourForm form = TourForm.builder()
                .id(updatable.getId())
                .name("NEW NAME")
                .tourDays(updatable.getTourDays())
                .description("NEW DESCRIPTION")
                .costs(updatable.getCosts())
                .duration(updatable.getDuration().toString())
                .guideId(updatable.getGuide().getId())
                .placeId(updatable.getPlace().getId())
                .build();
        tourService.update(updatable.getId(), form);
        TourEntity updated = tourRepository.getById(updatable.getId());
        assertEquals(form.getName(), updated.getName());
        assertEquals(form.getDescription(), updated.getDescription());
    }

    @Test
    @Transactional
    void deleteByIdTest() {
        List<TourEntity> allTours = tourRepository.findAll();
        TourEntity deleted = allTours.get(0);
        tourService.deleteById(deleted.getId());
        List<TourEntity> allToursAfterDeleting = tourRepository.findAll();
        assertNotEquals(allTours.size(), allToursAfterDeleting.size());
        assertFalse(allToursAfterDeleting.stream().anyMatch(tour -> tour.getName().equals(deleted.getName())));
    }

}

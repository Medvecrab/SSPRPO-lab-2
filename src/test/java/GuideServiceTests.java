import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tselebrovskii.Application;
import tselebrovskii.entity.GuideEntity;
import tselebrovskii.repository.GuideRepository;
import tselebrovskii.service.GuideService;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
public class GuideServiceTests {

    private static final int GUIDES_COUNT = 5;

    @Autowired
    GuideRepository guideRepository;

    @Autowired
    GuideService guideService;

    @BeforeEach
    void initDB() {
        for (int i = 0; i < GUIDES_COUNT; i++)
            guideRepository.save(GuideEntity.builder()
                    .surname("Guide Surname #" + i)
                    .name("Guide Name #" + i)
                    .workDays("")
                    .salary(BigInteger.valueOf(0L))
                    .build());
    }

    @AfterEach
    void clearDB() {
        guideRepository.deleteAll();
    }

    @Test
    void loadComponent() {
        assertNotNull(guideService);
    }

    @Test
    void findAllTest() {
        assertEquals(GUIDES_COUNT, guideService.findAll().size());
    }

}

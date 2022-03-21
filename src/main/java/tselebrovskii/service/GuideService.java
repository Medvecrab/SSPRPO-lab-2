package tselebrovskii.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tselebrovskii.entity.GuideEntity;
import tselebrovskii.repository.GuideRepository;

import java.util.List;

@Service
public class GuideService {

    @Autowired
    GuideRepository guideRepository;

    public List<GuideEntity> findAll() {
        return guideRepository.findAll();
    }
}

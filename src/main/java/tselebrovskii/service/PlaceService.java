package tselebrovskii.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tselebrovskii.entity.PlaceEntity;
import tselebrovskii.repository.PlaceRepository;

import java.util.List;

@Service
public class PlaceService {

    @Autowired
    PlaceRepository placeRepository;

    public List<PlaceEntity> findAll() {
        return placeRepository.findAll();
    }
}

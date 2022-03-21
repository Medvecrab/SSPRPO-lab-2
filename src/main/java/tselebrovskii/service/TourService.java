package tselebrovskii.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tselebrovskii.entity.GuideEntity;
import tselebrovskii.entity.PlaceEntity;
import tselebrovskii.entity.TourEntity;
import tselebrovskii.form.TourForm;
import tselebrovskii.repository.GuideRepository;
import tselebrovskii.repository.PlaceRepository;
import tselebrovskii.repository.TourRepository;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Service
public class TourService {

    @Autowired
    TourRepository tourRepository;

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    GuideRepository guideRepository;

    @Transactional
    public List<TourEntity> findAll() {
        return tourRepository.findAll();
    }

    @Transactional
    public TourEntity findById(Long id) {
        return tourRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(TourForm form) {
        tourRepository.save(formToTour(form));
    }

    @Transactional
    public void update(Long id, TourForm form) {
        Optional<TourEntity> tourEntityOptional = tourRepository.findById(id);
        if (tourEntityOptional.isPresent()) {
            form.setId(id);
            tourRepository.save(formToTour(form));
        }
    }

    @Transactional
    public void deleteById(Long id) {
        Optional<TourEntity> tourEntityOptional = tourRepository.findById(id);
        if (tourEntityOptional.isPresent()) {
            tourRepository.deleteById(id);
        }
    }

    private TourEntity formToTour(TourForm form) {
        GuideEntity guide = guideRepository.getById(form.getGuideId());
        PlaceEntity place = placeRepository.getById(form.getPlaceId());
        Time duration;
        if (form.getDuration().split(":").length == 3)
            duration = Time.valueOf(form.getDuration());
        else
            duration = Time.valueOf(form.getDuration() + ":00");
        return TourEntity.builder()
                .id(form.getId())
                .name(form.getName())
                .description(form.getDescription())
                .duration(duration)
                .costs(form.getCosts())
                .tourDays(form.getTourDays())
                .guide(guide)
                .place(place)
                .build();
    }

}

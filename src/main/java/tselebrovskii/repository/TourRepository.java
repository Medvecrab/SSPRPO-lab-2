package tselebrovskii.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tselebrovskii.entity.TourEntity;

/**
 * Репозиторий для доступа к таблице экскурсий
 */
@Repository
public interface TourRepository extends JpaRepository<TourEntity, Long> {
}

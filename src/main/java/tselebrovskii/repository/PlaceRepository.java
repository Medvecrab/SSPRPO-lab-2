package tselebrovskii.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tselebrovskii.entity.PlaceEntity;

/**
 * Репозиторий для доступа к таблице городов
 */
@Repository
public interface PlaceRepository extends JpaRepository<PlaceEntity, Long> {
}

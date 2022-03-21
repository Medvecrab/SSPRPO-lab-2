package tselebrovskii.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tselebrovskii.entity.GuideEntity;

/**
 * Репозиторий для доступа к таблице гидов
 */
@Repository
public interface GuideRepository extends JpaRepository<GuideEntity, Long> {
}

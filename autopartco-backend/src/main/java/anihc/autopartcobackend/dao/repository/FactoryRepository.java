package anihc.autopartcobackend.dao.repository;

import anihc.autopartcobackend.dao.entity.FactoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FactoryRepository extends JpaRepository<FactoryEntity, Long> {

    @Query("SELECT DISTINCT f FROM FactoryEntity f WHERE f.factoryNameEn IS NOT NULL OR f.factoryNameZh IS NOT NULL ORDER BY f.factoryNameEn")
    List<FactoryEntity> findAllFactoriesWithNames();
}

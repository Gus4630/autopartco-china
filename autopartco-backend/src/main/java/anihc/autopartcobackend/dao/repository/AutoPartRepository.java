package anihc.autopartcobackend.dao.repository;

import anihc.autopartcobackend.dao.entity.AutoPartEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoPartRepository extends JpaRepository<AutoPartEntity, Long>,
        JpaSpecificationExecutor<AutoPartEntity> {

    Optional<AutoPartEntity> findBySlug(String slug);

    @Modifying
    @Query(value = "UPDATE auto_parts SET is_active = true WHERE id = :autoPartId", nativeQuery = true)
    void activateAutoPart(@Param("autoPartId") Long autoPartId);

    @Query(value = "SELECT COUNT(*) > 0 FROM auto_parts WHERE id = :autoPartId", nativeQuery = true)
    boolean existsByIdIgnoringActiveStatus(@Param("autoPartId") Long autoPartId);

    @Query("SELECT DISTINCT ap FROM AutoPartEntity ap WHERE ap.categoryEn = :category")
    Page<AutoPartEntity> findByCategory(@Param("category") String category, Pageable pageable);

    @Query("SELECT DISTINCT ap.categoryEn, ap.categoryZh FROM AutoPartEntity ap "
            + "WHERE ap.categoryEn IS NOT NULL")
    List<Object[]> findDistinctCategories();

    @Query("SELECT COUNT(ap) FROM AutoPartEntity ap WHERE ap.categoryEn = :categoryEn")
    Long countByCategory(@Param("categoryEn") String categoryEn);
}

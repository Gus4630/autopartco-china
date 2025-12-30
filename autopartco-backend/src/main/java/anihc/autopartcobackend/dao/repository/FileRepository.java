package anihc.autopartcobackend.dao.repository;

import anihc.autopartcobackend.dao.entity.FileEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {

    boolean existsByFilePath(String filePath);

    Optional<FileEntity> findByFilePath(String filePath);

    @Modifying
    @Query(value = "INSERT INTO auto_part_files (auto_part_id, file_id, display_order, is_primary) "
                   + "VALUES (:autoPartId, :fileId, 0, true)", nativeQuery = true)
    void linkFileToAutoPart(@Param("autoPartId") Long autoPartId, @Param("fileId") Long fileId);
}

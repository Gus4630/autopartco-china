package anihc.autopartcobackend.controller;

import anihc.autopartcobackend.dao.entity.FileEntity;
import anihc.autopartcobackend.dao.repository.FileRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@Tag(name = "Files", description = "File serving endpoints")
public class FileController {

    private final FileRepository fileRepository;

    @GetMapping("/{id}")
    @Operation(summary = "Get file by ID", description = "Serves image files for auto parts by file ID")
    public ResponseEntity<Resource> getFile(@PathVariable Long id) {
        try {
            FileEntity fileEntity = fileRepository.findById(id)
                    .orElseGet(() -> {
                        log.warn("File not found with ID: {}", id);
                        return null;
                    });

            if (fileEntity == null) {
                return ResponseEntity.notFound().build();
            }

            Path filePath = Paths.get(fileEntity.getFilePath());

            if (!Files.exists(filePath)) {
                log.warn("Physical file not found: {}", fileEntity.getFilePath());
                return ResponseEntity.notFound().build();
            }

            Resource resource = new FileSystemResource(filePath);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(fileEntity.getMimeType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileEntity.getFileName() + "\"")
                    .body(resource);

        } catch (Exception e) {
            log.error("Error serving file with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

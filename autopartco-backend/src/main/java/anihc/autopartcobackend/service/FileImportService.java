package anihc.autopartcobackend.service;

import anihc.autopartcobackend.dao.entity.FileEntity;
import anihc.autopartcobackend.dao.repository.AutoPartRepository;
import anihc.autopartcobackend.dao.repository.FileRepository;
import jakarta.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FileImportService implements CommandLineRunner {

    private static final String PICTURES_PATH = "files/pictures";
    private static final Pattern ID_PATTERN = Pattern.compile("^(\\d+)");

    private final FileRepository fileRepository;
    private final AutoPartRepository autoPartRepository;
    private final FileImportService self;

    public FileImportService(FileRepository fileRepository,
                            AutoPartRepository autoPartRepository,
                            @Lazy FileImportService self) {
        this.fileRepository = fileRepository;
        this.autoPartRepository = autoPartRepository;
        this.self = self;
    }

    @Override
    public void run(String... args) {
        log.info("Starting file import process...");

        try {
            File picturesDir = new ClassPathResource(PICTURES_PATH).getFile();

            if (!picturesDir.exists() || !picturesDir.isDirectory()) {
                log.warn("Pictures directory not found: {}", PICTURES_PATH);
                return;
            }

            File[] files = picturesDir.listFiles();
            if (files == null || files.length == 0) {
                log.warn("No files found in pictures directory");
                return;
            }

            int successCount = 0;
            int skipCount = 0;

            for (File file : files) {
                if (file.isFile()) {
                    try {
                        if (self.processFile(file)) {
                            successCount++;
                        } else {
                            skipCount++;
                        }
                    } catch (Exception e) {
                        log.error("Error processing file: {}", file.getName(), e);
                        skipCount++;
                    }
                }
            }

            log.info("File import completed. Success: {}, Skipped: {}", successCount, skipCount);

        } catch (IOException e) {
            log.error("Error accessing pictures directory", e);
        }
    }

    @Transactional
    protected boolean processFile(File file) {
        Long autoPartId = extractIdFromFilename(file.getName());

        if (autoPartId == null) {
            log.warn("Could not extract ID from filename: {}", file.getName());
            return false;
        }

        if (!autoPartRepository.existsById(autoPartId)) {
            log.warn("AutoPart not found for ID {} (file: {})", autoPartId, file.getName());
            return false;
        }

        // Check if file already exists
        String filePath = PICTURES_PATH + "/" + file.getName();
        Optional<FileEntity> existingFile = fileRepository.findByFilePath(filePath);

        FileEntity fileEntity;
        if (existingFile.isPresent()) {
            // Update existing file with correct URL format
            fileEntity = existingFile.get();
            fileEntity.setUrl("/api/files/" + fileEntity.getId());
            fileEntity = fileRepository.save(fileEntity);
            log.debug("Updated file URL: {}", file.getName());
        } else {
            // Create new file entity
            fileEntity = FileEntity.builder()
                    .fileName(file.getName())
                    .filePath(filePath)
                    .fileSize(file.length())
                    .mimeType(getMimeType(file.getName()))
                    .build();

            fileEntity = fileRepository.save(fileEntity);

            // Update URL with file ID
            fileEntity.setUrl("/api/files/" + fileEntity.getId());
            fileEntity = fileRepository.save(fileEntity);

            // Link to auto part using native query to avoid lazy loading issues
            fileRepository.linkFileToAutoPart(autoPartId, fileEntity.getId());

            // Activate the auto part since it now has a file
            autoPartRepository.activateAutoPart(autoPartId);

            log.debug("Imported file {} for auto part ID {}", file.getName(), autoPartId);
        }

        return true;
    }

    private Long extractIdFromFilename(String filename) {
        Matcher matcher = ID_PATTERN.matcher(filename);
        if (matcher.find()) {
            try {
                return Long.parseLong(matcher.group(1));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    private String getMimeType(String filename) {
        String extension = "";
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex > 0) {
            extension = filename.substring(lastDotIndex + 1).toLowerCase();
        }

        return switch (extension) {
            case "jpg", "jpeg" -> "image/jpeg";
            case "png" -> "image/png";
            case "webp" -> "image/webp";
            case "gif" -> "image/gif";
            case "avif" -> "image/avif";
            default -> "application/octet-stream";
        };
    }
}

package anihc.autopartcobackend.controller;

import static org.springframework.http.HttpStatus.OK;

import anihc.autopartcobackend.model.response.CsvCleaningResponse;
import anihc.autopartcobackend.service.GeminiDescriptionCleanerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/csv")
@RequiredArgsConstructor
public class CsvCleanerController {

    private final GeminiDescriptionCleanerService cleanerService;

    @PostMapping("/clean-descriptions")
    public ResponseEntity<CsvCleaningResponse> cleanDescriptions(
            @RequestParam(required = false) Integer rowsToProcess) {
        // Triggers the async process
        return ResponseEntity.status(OK).body(cleanerService.initiateCleaning(rowsToProcess));
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        int processed = cleanerService.getProcessedCount();
        return ResponseEntity.ok("Processed " + processed + " rows so far.");
    }
}
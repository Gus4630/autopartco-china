package anihc.autopartcobackend.service;

import anihc.autopartcobackend.model.response.CsvCleaningResponse;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import jakarta.annotation.PostConstruct;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeminiDescriptionCleanerService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private Client client;
    private final AtomicInteger processedRowsTracker = new AtomicInteger(0);

    private static final String INPUT_PATH = "src/main/resources/files/Tender.csv";
    private static final String OUTPUT_PATH = "src/main/resources/files/Tender_cleaned.csv";
    private static final int BATCH_SIZE = 30; // Increased for efficiency

    @PostConstruct
    public void init() {
        this.client = Client.builder().apiKey(apiKey).build();
        log.info("Gemini SDK initialized for background processing");
    }

    public int getProcessedCount() {
        return processedRowsTracker.get();
    }

    /**
     * Entry point for the controller.
     * If rowsToProcess is high, it triggers the Async process.
     */
    public CsvCleaningResponse initiateCleaning(Integer rowsToProcess) {
        List<String[]> allRows = readCsv(INPUT_PATH);
        int totalDataRows = allRows.size() - 1;
        int targetRows = (rowsToProcess == null || rowsToProcess <= 0)
                ? totalDataRows : Math.min(rowsToProcess, totalDataRows);

        // Reset tracker
        processedRowsTracker.set(0);

        // Start the background process
        processAllAsync(allRows, targetRows);

        return CsvCleaningResponse.builder()
                .totalRows(totalDataRows)
                .processedRows(0) // Will update via status endpoint
                .outputFilePath(OUTPUT_PATH)
                .message("Background processing started for " + targetRows
                        + " rows. Use /status to track progress.")
                .build();
    }

    @Async
    protected void processAllAsync(List<String[]> allRows, int rowsToProcess) {
        String[] header = allRows.get(0);
        List<String[]> dataRows = allRows.subList(1, allRows.size());
        List<String[]> targetList = dataRows.subList(0, rowsToProcess);

        List<List<String[]>> batches = partitionList(targetList, BATCH_SIZE);

        for (int i = 0; i < batches.size(); i++) {
            try {
                cleanBatch(batches.get(i));
                processedRowsTracker.addAndGet(batches.get(i).size());

                log.info("Progress: {}/{} rows cleaned", processedRowsTracker.get(), rowsToProcess);

                // Checkpoint save every 5 batches to prevent data loss
                if (i % 5 == 0) {
                    writeCsv(OUTPUT_PATH, header, targetList);
                }

                // Rate limiting for paid tier (adjust to 4000 for free tier)
                Thread.sleep(1000);
            } catch (Exception e) {
                log.error("Error processing batch {}: {}", i, e.getMessage());
            }
        }

        writeCsv(OUTPUT_PATH, header, targetList);
        log.info("Full CSV cleaning complete. Saved to {}", OUTPUT_PATH);
    }

    private void cleanBatch(List<String[]> batch) {
        List<String> descriptions = extractDescriptions(batch);
        if (descriptions.isEmpty()) {
            return;
        }

        String prompt = buildPrompt(descriptions);
        GenerateContentResponse response = client.models.generateContent(
                "gemini-2.0-flash-lite", prompt, null);

        String[] cleanedLines = response.text().trim().split("\n");
        updateBatchWithCleanedDescriptions(batch, cleanedLines);
    }

    private String buildPrompt(List<String> descriptions) {
        StringBuilder sb = new StringBuilder();
        sb.append("### ROLE\n");
        sb.append("You are a Technical Copywriter for a global auto parts catalog. "
                + "Your goal is to convert messy repair notes into professional "
                + "English product descriptions.\n\n");

        sb.append("### GUIDELINES\n");
        sb.append("1. STRUCTURE: Each description should state the Part Name "
                + "followed by its primary function or quality.\n");
        sb.append("2. TONE: Professional, technical, and concise.\n");
        sb.append("3. CLEANING: Remove all verbs like 'Replacing' or 'Change'. "
                + "Remove all local slang like '(vıjimnoy)' or '(podpresin)'.\n");
        sb.append("4. STANDARD PHRASES: Use phrases like 'engineered for durability', "
                + "'precision-fit component', or 'OEM-quality replacement'.\n");
        sb.append("5. OUTPUT: Exactly one description per line. No extra text.\n\n");

        sb.append("### EXAMPLES\n");
        sb.append("Input: Cardan shaft cross member (krestovinin) replacement "
                + "with spare parts\n");
        sb.append("Output: Heavy-duty Cardan Shaft Cross Member engineered "
                + "for precise drivetrain alignment and vibration reduction.\n");
        sb.append("Input: Master brake (qlavnıy) cylinder replacement "
                + "including new reservoir\n");
        sb.append("Output: Premium Master Brake Cylinder designed for optimal "
                + "hydraulic pressure and reliable braking performance.\n");
        sb.append("Input: Windshield (podpresin) change\n");
        sb.append("Output: High-clarity Front Windshield manufactured to OEM "
                + "safety specifications for a perfect fit and visibility.\n\n");

        sb.append("### DATA TO PROCESS\n");
        for (String desc : descriptions) {
            sb.append(desc).append("\n");
        }

        return sb.toString();
    }


    private List<String> extractDescriptions(List<String[]> batch) {
        return batch.stream()
                .map(row -> row.length > 2 ? row[2] : "")
                .collect(Collectors.toList());
    }

    private void updateBatchWithCleanedDescriptions(List<String[]> batch, String[] cleanedLines) {
        for (int i = 0; i < batch.size() && i < cleanedLines.length; i++) {
            if (batch.get(i).length > 2) {
                // Remove AI-generated bullets or leading dashes
                batch.get(i)[2] = cleanedLines[i].replaceFirst("^[-*\\d.\\s]+", "").trim();
            }
        }
    }

    private List<String[]> readCsv(String path) {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(path))
                .withCSVParser(new com.opencsv.CSVParserBuilder().withSeparator(';').build())
                .build()) {
            return reader.readAll();
        } catch (IOException | CsvException e) {
            throw new UncheckedIOException("Read failed", (IOException) e);
        }
    }

    private void writeCsv(String path, String[] header, List<String[]> dataRows) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(path), ';',
                CSVWriter.DEFAULT_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {
            writer.writeNext(header);
            dataRows.forEach(writer::writeNext);
        } catch (IOException e) {
            throw new UncheckedIOException("Write failed", e);
        }
    }

    private <T> List<List<T>> partitionList(List<T> list, int size) {
        List<List<T>> partitions = new ArrayList<>();
        for (int i = 0; i < list.size(); i += size) {
            partitions.add(list.subList(i, Math.min(i + size, list.size())));
        }
        return partitions;
    }
}
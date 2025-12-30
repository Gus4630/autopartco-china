package anihc.autopartcobackend.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CsvCleaningResponse {

    private Integer totalRows;
    private Integer processedRows;
    private String outputFilePath;
    private String message;
}
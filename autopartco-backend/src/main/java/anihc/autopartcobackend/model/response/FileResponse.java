package anihc.autopartcobackend.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {
    private Long id;
    private String url;
    private String fileName;
    private Long fileSize;
    private String mimeType;
    private Boolean isPrimary;
    private Integer displayOrder;
}

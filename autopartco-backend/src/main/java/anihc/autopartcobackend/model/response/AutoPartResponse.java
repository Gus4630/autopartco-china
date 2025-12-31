package anihc.autopartcobackend.model.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoPartResponse {
    private Long id;
    private String slug;
    private String partNumber;

    private String nameEn;
    private String nameZh;

    private String descriptionEn;
    private String descriptionZh;

    private String categoryEn;
    private String categoryZh;

    private String manufacturingCountryEn;
    private String manufacturingCountryZh;

    private String conditionEn;
    private String conditionZh;

    private String quality;

    private String carBrandEn;
    private String carBrandZh;

    private String carModelEn;
    private String carModelZh;

    private Integer partYear;

    private Boolean isActive;

    private FactoryResponse factory;

    private List<FileResponse> files;
}

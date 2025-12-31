package anihc.autopartcobackend.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FactoryResponse {
    private Long id;
    private String factoryNameEn;
    private String factoryNameZh;
    private String factoryPartEn;
    private String factoryPartZh;
    private String factoryPhoneNumber;
    private String factorySiteUrl;
}

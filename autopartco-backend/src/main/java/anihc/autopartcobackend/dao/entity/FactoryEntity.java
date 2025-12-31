package anihc.autopartcobackend.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "factories")
public class FactoryEntity extends BaseEntity {

    @Column(name = "factory_name_en", length = 200)
    private String factoryNameEn;

    @Column(name = "factory_name_zh", length = 200)
    private String factoryNameZh;

    @Column(name = "factory_part_en", length = 200)
    private String factoryPartEn;

    @Column(name = "factory_part_zh", length = 200)
    private String factoryPartZh;

    @Column(name = "factory_phone_number", length = 50)
    private String factoryPhoneNumber;

    @Column(name = "factory_site_url", length = 500)
    private String factorySiteUrl;
}

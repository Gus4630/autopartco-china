package anihc.autopartcobackend.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"files", "factory"})
@EqualsAndHashCode(callSuper = true, exclude = {"files", "factory"})
@Entity
@Table(name = "auto_parts")
@SQLRestriction("is_active = true")
public class AutoPartEntity extends BaseEntity {

    @Column(name = "slug", unique = true, length = 255)
    private String slug;

    @Column(name = "part_number", length = 50)
    private String partNumber;

    // Names
    @Column(name = "name_en", length = 200)
    private String nameEn;

    @Column(name = "name_zh", length = 200)
    private String nameZh;

    // Descriptions
    @Column(name = "description_en", columnDefinition = "TEXT")
    private String descriptionEn;

    @Column(name = "description_zh", columnDefinition = "TEXT")
    private String descriptionZh;

    // Category
    @Column(name = "category_en", length = 100)
    private String categoryEn;

    @Column(name = "category_zh", length = 100)
    private String categoryZh;

    // Manufacturing Country
    @Column(name = "manufacturing_country_en", length = 100)
    private String manufacturingCountryEn;

    @Column(name = "manufacturing_country_zh", length = 100)
    private String manufacturingCountryZh;

    // Condition
    @Column(name = "condition_en", length = 50)
    private String conditionEn;

    @Column(name = "condition_zh", length = 50)
    private String conditionZh;

    // Quality
    @Column(name = "quality", length = 50)
    private String quality;

    // Car Brand
    @Column(name = "car_brand_en", length = 100)
    private String carBrandEn;

    @Column(name = "car_brand_zh", length = 100)
    private String carBrandZh;

    // Car Model
    @Column(name = "car_model_en", length = 100)
    private String carModelEn;

    @Column(name = "car_model_zh", length = 100)
    private String carModelZh;

    // Part Year
    @Column(name = "part_year")
    private Integer partYear;

    // Status
    @Column(name = "is_active")
    private Boolean isActive;

    // Factory relationship
    @ManyToOne
    @JoinColumn(name = "factory_id")
    private FactoryEntity factory;

    // Files relationship
    @ManyToMany
    @JoinTable(
            name = "auto_part_files",
            joinColumns = @JoinColumn(name = "auto_part_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id")
    )
    private Set<FileEntity> files;
}

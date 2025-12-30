package anihc.autopartcobackend.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;
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
@ToString(callSuper = true, exclude = "autoParts")
@EqualsAndHashCode(callSuper = true, exclude = "autoParts")
@Entity
@Table(name = "files")
public class FileEntity extends BaseEntity {

    @Column(name = "url", length = 500)
    private String url;

    @Column(name = "file_path", length = 500)
    private String filePath;

    @Column(name = "file_name", length = 255)
    private String fileName;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "mime_type", length = 100)
    private String mimeType;

    @ManyToMany(mappedBy = "files")
    private Set<AutoPartEntity> autoParts;
}

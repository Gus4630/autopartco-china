package anihc.autopartcobackend.service;

import anihc.autopartcobackend.dao.entity.AutoPartEntity;
import anihc.autopartcobackend.dao.repository.AutoPartRepository;
import anihc.autopartcobackend.exception.DataNotFoundException;
import anihc.autopartcobackend.model.response.AutoPartResponse;
import anihc.autopartcobackend.model.response.CategoryResponse;
import anihc.autopartcobackend.model.response.FileResponse;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AutoPartService {

    private final AutoPartRepository autoPartRepository;

    @Transactional
    public AutoPartResponse getBySlug(String slug) {
        AutoPartEntity entity = autoPartRepository.findBySlug(slug)
                .orElseThrow(() -> new DataNotFoundException("Auto part not found with slug: " + slug));

        return mapToResponse(entity);
    }

    @Transactional
    public Page<AutoPartResponse> getAllParts(String category, String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<AutoPartEntity> entities;

        if (category != null && !category.isEmpty()) {
            entities = autoPartRepository.findByCategory(category, pageable);
        } else {
            entities = autoPartRepository.findAll(pageable);
        }

        return entities.map(this::mapToResponse);
    }

    @Transactional
    public List<AutoPartResponse> getRelatedProducts(Long id, int limit) {
        AutoPartEntity entity = autoPartRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Auto part not found with id: " + id));

        String category = entity.getCategoryEn();
        Pageable pageable = PageRequest.of(0, limit);

        return autoPartRepository.findByCategory(category, pageable)
                .stream()
                .filter(e -> !e.getId().equals(id))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CategoryResponse> getAllCategories() {
        List<Object[]> categories = autoPartRepository.findDistinctCategories();
        return categories.stream()
                .map(category -> {
                    String categoryEn = (String) category[0];
                    String categoryZh = (String) category[1];
                    Long count = autoPartRepository.countByCategory(categoryEn);
                    return CategoryResponse.builder()
                            .nameEn(categoryEn)
                            .nameZh(categoryZh)
                            .productCount(count)
                            .build();
                })
                .collect(Collectors.toList());
    }

    private AutoPartResponse mapToResponse(AutoPartEntity entity) {
        return AutoPartResponse.builder()
                .id(entity.getId())
                .slug(entity.getSlug())
                .partNumber(entity.getPartNumber())
                .nameEn(entity.getNameEn())
                .nameZh(entity.getNameZh())
                .descriptionEn(entity.getDescriptionEn())
                .descriptionZh(entity.getDescriptionZh())
                .categoryEn(entity.getCategoryEn())
                .categoryZh(entity.getCategoryZh())
                .factoryAddressEn(entity.getFactoryAddressEn())
                .factoryAddressZh(entity.getFactoryAddressZh())
                .manufacturingCountryEn(entity.getManufacturingCountryEn())
                .manufacturingCountryZh(entity.getManufacturingCountryZh())
                .conditionEn(entity.getConditionEn())
                .conditionZh(entity.getConditionZh())
                .quality(entity.getQuality())
                .carBrandEn(entity.getCarBrandEn())
                .carBrandZh(entity.getCarBrandZh())
                .carModelEn(entity.getCarModelEn())
                .carModelZh(entity.getCarModelZh())
                .partYear(entity.getPartYear())
                .isActive(entity.getIsActive())
                .files(entity.getFiles() == null ? null :
                    entity.getFiles().stream()
                        .map(file -> FileResponse.builder()
                                .id(file.getId())
                                .url(file.getUrl())
                                .fileName(file.getFileName())
                                .fileSize(file.getFileSize())
                                .mimeType(file.getMimeType())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}

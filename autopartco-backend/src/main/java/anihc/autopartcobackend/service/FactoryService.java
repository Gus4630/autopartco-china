package anihc.autopartcobackend.service;

import anihc.autopartcobackend.dao.entity.FactoryEntity;
import anihc.autopartcobackend.dao.repository.FactoryRepository;
import anihc.autopartcobackend.model.response.FactoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FactoryService {

    private final FactoryRepository factoryRepository;

    @Transactional(readOnly = true)
    public List<FactoryResponse> getAllFactories() {
        List<FactoryEntity> factories = factoryRepository.findAllFactoriesWithNames();
        return factories.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private FactoryResponse mapToResponse(FactoryEntity entity) {
        return FactoryResponse.builder()
                .id(entity.getId())
                .factoryNameEn(entity.getFactoryNameEn())
                .factoryNameZh(entity.getFactoryNameZh())
                .factoryPartEn(entity.getFactoryPartEn())
                .factoryPartZh(entity.getFactoryPartZh())
                .factoryPhoneNumber(entity.getFactoryPhoneNumber())
                .factorySiteUrl(entity.getFactorySiteUrl())
                .build();
    }
}

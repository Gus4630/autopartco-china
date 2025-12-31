package anihc.autopartcobackend.specification;

import static anihc.autopartcobackend.specification.BaseSpecification.equalPredicate;
import static anihc.autopartcobackend.specification.BaseSpecification.ilikePredicate;

import anihc.autopartcobackend.dao.entity.AutoPartEntity;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class AutoPartSpecification {

    public static Specification<AutoPartEntity> getSpecification(String category, String search) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filter by category
            equalPredicate(category, root.get("categoryEn"), criteriaBuilder)
                    .ifPresent(predicates::add);

            // Search across multiple fields
            if (search != null && !search.trim().isEmpty()) {
                String searchTerm = "%" + search.toLowerCase() + "%";
                Predicate searchPredicate = criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("nameEn")), searchTerm),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("nameZh")), searchTerm),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("partNumber")), searchTerm)
                );
                predicates.add(searchPredicate);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

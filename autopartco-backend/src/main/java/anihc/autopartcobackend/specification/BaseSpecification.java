package anihc.autopartcobackend.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BaseSpecification {

    public static <T> Optional<Predicate> equalPredicate(T value, Path<T> path, CriteriaBuilder criteriaBuilder) {
        return Optional.ofNullable(value).map(v -> criteriaBuilder.equal(path, v));
    }

    public static Optional<Predicate> likePredicate(String value, Path<String> path, CriteriaBuilder criteriaBuilder) {
        return Optional.ofNullable(value)
                .filter(v -> !v.isBlank())
                .map(v -> criteriaBuilder.like(criteriaBuilder.lower(path), wrapSearchTerm(v)));
    }

    public static <T extends Comparable<T>> Optional<Predicate> rangePredicate(T value,
                                                                               String condition, Path<T> path,
                                                                               CriteriaBuilder criteriaBuilder) {
        if (value == null) {
            return Optional.empty();
        }

        return Optional.of(
                switch (Optional.ofNullable(condition).orElse("=")) {
                    case ">" -> criteriaBuilder.greaterThan(path, value);
                    case ">=" -> criteriaBuilder.greaterThanOrEqualTo(path, value);
                    case "<" -> criteriaBuilder.lessThan(path, value);
                    case "<=" -> criteriaBuilder.lessThanOrEqualTo(path, value);
                    default -> criteriaBuilder.equal(path, value);
                });
    }

    public static Optional<Predicate> ilikePredicate(String value, Path<String> path, CriteriaBuilder criteriaBuilder) {
        return Optional.ofNullable(value)
                .filter(v -> !v.isBlank())
                .map(v -> criteriaBuilder.like(criteriaBuilder.lower(path), wrapSearchTerm(v)));
    }

    public static Optional<Predicate> ilikeAnyPredicate(List<String> values, Path<String> path,
                                                        CriteriaBuilder criteriaBuilder) {
        if (values == null || values.isEmpty()) {
            return Optional.empty();
        }

        List<Predicate> predicates = values.stream()
                .filter(v -> v != null && !v.trim().isEmpty())
                .map(v -> criteriaBuilder.like(criteriaBuilder.lower(path), wrapSearchTerm(v)))
                .toList();

        if (predicates.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
    }

    public static <T> Optional<Predicate> inPredicate(Collection<T> values,
                                                      Path<T> path, CriteriaBuilder criteriaBuilder) {
        if (values == null || values.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(path.in(values));
    }

    private static String wrapSearchTerm(String term) {
        return "%" + term.toLowerCase() + "%";
    }
}

package com.jcmbeng.fundtransfer.utils;import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class SpecificationUtils {

    /**
     * Returns a Specification that matches all records (similar to "1=1" in SQL).
     *
     * @param <T> the entity type
     * @return a Specification that always returns true
     */
    public static <T> Specification<T> alwaysTrue() {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) ->
                criteriaBuilder.conjunction();
    }
}


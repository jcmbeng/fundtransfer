package com.jcmbeng.fundtransfer.repositories.specifications;

import com.jcmbeng.fundtransfer.entities.Client;
import com.jcmbeng.fundtransfer.enums.CriteriaFilter;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

/**
 * Specification class for creating custom filters on {@link Client} entities based on various criteria.
 *
 * <p>This class provides a method for generating {@link Specification} objects that allow filtering
 * of {@link Client} fields based on content and a specified {@link CriteriaFilter}.</p>
 */
public class ClientSpecification {

    /**
     * Creates a {@link Specification} to filter {@link Client} entities based on a specified field, content, and filter type.
     *
     * @param field     the name of the {@link Client} field to apply the filter on (e.g., "name", "email")
     * @param content   the content to search for within the specified field
     * @param predicate the {@link CriteriaFilter} that determines the type of filtering to apply (e.g., LIKE, EQUALS)
     * @return a {@link Specification} that can be used to filter {@link Client} entities, or {@code null} if the content is empty
     * @see CriteriaFilter
     */
    public static Specification<Client> hasContent(final String field, final String content,
                                                   final CriteriaFilter predicate) {
        return (Root<Client> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (content == null || content.isEmpty()) {
                return null;
            }
            if (predicate.equals(CriteriaFilter.LIKE)) {
                return builder.like(root.get(field), "%" + content + "%");
            } else if (predicate.equals(CriteriaFilter.STARTS_LIKE)) {
                return builder.like(root.get(field), "%" + content);
            } else if (predicate.equals(CriteriaFilter.ENDS_LIKE)) {
                return builder.like(root.get(field), content + "%");
            } else if (predicate.equals(CriteriaFilter.EQUALS)) {
                return builder.equal(root.get(field), content);
            }
            return null;
        };
    }
}

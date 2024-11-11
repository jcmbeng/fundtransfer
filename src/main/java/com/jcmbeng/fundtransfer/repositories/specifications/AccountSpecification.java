package com.jcmbeng.fundtransfer.repositories.specifications;

import com.jcmbeng.fundtransfer.entities.Account;
import com.jcmbeng.fundtransfer.entities.Transaction;
import com.jcmbeng.fundtransfer.enums.AccountStatus;
import com.jcmbeng.fundtransfer.enums.CriteriaFilter;
import com.jcmbeng.fundtransfer.enums.TransactionMethod;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

/**
 * Specification class for creating custom filters on {@link Account} entities based on various criteria.
 *
 * <p>This class provides a method for generating {@link Specification} objects that allow filtering
 * of {@link Account} fields based on content and a specified {@link CriteriaFilter}.</p>
 */
public class AccountSpecification {

    /**
     * Creates a {@link Specification} to filter {@link Account} entities based on a specified field, content, and filter type.
     *
     * @param field     the name of the {@link Account} field to apply the filter on (e.g., "name", "email")
     * @param content   the content to search for within the specified field
     * @param predicate the {@link CriteriaFilter} that determines the type of filtering to apply (e.g., LIKE, EQUALS)
     * @return a {@link Specification} that can be used to filter {@link Account} entities, or {@code null} if the content is empty
     * @see CriteriaFilter
     */
    public static Specification<Account> hasContent(final String field,
                                                    final String content,
                                                    final CriteriaFilter predicate) {
        return (Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (content == null || content.isEmpty()) {
                return null;
            }
            if (predicate.equals(CriteriaFilter.EQUALS)) {
                return builder.equal(root.get(field), content);
            }
            else if (predicate.equals(CriteriaFilter.GREATER)) {
                return builder.greaterThan (root.get(field), content);
            }
            else if (predicate.equals(CriteriaFilter.LOWER)) {
                return builder.lessThan (root.get(field), new BigDecimal(content));
            }
            else if (predicate.equals(CriteriaFilter.GREATER_OR_EQUALS)) {
                return builder.greaterThanOrEqualTo (root.get(field), content);
            }
            else if (predicate.equals(CriteriaFilter.LOWER_OR_EQUALS)) {
                return builder.lessThanOrEqualTo (root.get(field), content);
            }
            return null;
        };
    }

       public static Specification<Account> hasStatus(AccountStatus accountStatus) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), accountStatus);
    }
}

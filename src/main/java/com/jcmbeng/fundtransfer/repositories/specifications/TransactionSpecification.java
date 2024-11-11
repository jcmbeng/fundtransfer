package com.jcmbeng.fundtransfer.repositories.specifications;

import com.jcmbeng.fundtransfer.entities.Client;
import com.jcmbeng.fundtransfer.entities.Transaction;
import com.jcmbeng.fundtransfer.enums.CriteriaFilter;
import com.jcmbeng.fundtransfer.enums.TransactionMethod;
import com.jcmbeng.fundtransfer.enums.TransactionStatus;
import com.jcmbeng.fundtransfer.enums.TransactionType;
import com.jcmbeng.fundtransfer.utils.EnumUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Specification class for creating custom filters on {@link Transaction} entities based on various criteria.
 *
 * <p>This class provides a method for generating {@link Specification} objects that allow filtering
 * of {@link Transaction} fields based on content and a specified {@link CriteriaFilter}.</p>
 */
public class TransactionSpecification {



    // Filter by account Number
    public static Specification<Transaction> hasAccountAccountNumber(String accountNumber) {
        return (root, query, criteriaBuilder) ->
                accountNumber != null ? criteriaBuilder.equal(root.get("account").get("accountNumber"), accountNumber) : null;
    }


    // Filter by account ID
    public static Specification<Transaction> hasAccountId(Long accountId) {
        return (root, query, criteriaBuilder) ->
                accountId != null ? criteriaBuilder.equal(root.get("account").get("id"), accountId) : null;
    }

    // Filter by transaction type (DEBIT or CREDIT)
    public static Specification<Transaction> hasTransactionType(TransactionType transactionType) {
        return (root, query, criteriaBuilder) -> transactionType !=null  ?
                criteriaBuilder.equal(root.get("type"), transactionType) : null;
    }

    // Filter by transaction type (DEBIT or CREDIT)
    public static Specification<Transaction> hasTransactionMethod(TransactionMethod transactionMethod) {
        return (root, query, criteriaBuilder) -> transactionMethod !=null  ?
                criteriaBuilder.equal(root.get("method"), transactionMethod) : null;
    }

    // Filter by transaction status (e.g., SUCCESS, FAILED, PENDING)
    public static Specification<Transaction> hasTransactionStatus(TransactionStatus transactionStatus) {
        return (root, query, criteriaBuilder) -> transactionStatus !=null  ?
                        criteriaBuilder.equal(root.get("status"), transactionStatus) : null;
    }

    // Filter transactions greater than or equal to a certain amount
    public static Specification<Transaction> amountGreaterThanOrEqual(BigDecimal amount) {
        return (root, query, criteriaBuilder) ->
                amount != null ? criteriaBuilder.greaterThanOrEqualTo (root.get("amount"), amount) : null;
    }

    // Filter transactions less than or equal to a certain amount
    public static Specification<Transaction> amountLessThanOrEqual(BigDecimal amount) {
        return (root, query, criteriaBuilder) ->
                amount != null ? criteriaBuilder.lessThanOrEqualTo (root.get("amount"), amount) : null;
    }

    // Filter by a specific date range (from and to)
    public static Specification<Transaction> timestampBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return (root, query, criteriaBuilder) ->
                (startDate != null && endDate !=null) ? criteriaBuilder.between(root.get("timestamp"), startDate,
                        endDate) : null;
    }
}

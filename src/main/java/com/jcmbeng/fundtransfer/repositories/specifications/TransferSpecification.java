package com.jcmbeng.fundtransfer.repositories.specifications;

import com.jcmbeng.fundtransfer.entities.Transfer;
import com.jcmbeng.fundtransfer.enums.TransferStatus;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferSpecification {

    // Filter by transfer reference if provided
    public static Specification<Transfer> hasTransferReference(String transferReference) {
        return (root, query, criteriaBuilder) ->
                transferReference == null ? null :
                        criteriaBuilder.equal(root.get("transferReference"), transferReference);
    }

    // Filter by debit reference if provided
    public static Specification<Transfer> hasDebitReference(String debitReference) {
        return (root, query, criteriaBuilder) ->
                debitReference == null ? null :
                        criteriaBuilder.equal(root.get("debitReference"), debitReference);
    }

    // Filter by credit reference if provided
    public static Specification<Transfer> hasCreditReference(String creditReference) {
        return (root, query, criteriaBuilder) ->
                creditReference == null ? null :
                        criteriaBuilder.equal(root.get("creditReference"), creditReference);
    }

    // Filter by source account (fromAccount) if provided
    public static Specification<Transfer> hasFromAccount(Long fromAccountId) {
        return (root, query, criteriaBuilder) ->
                fromAccountId == null ? null :
                        criteriaBuilder.equal(root.get("fromAccount").get("id"), fromAccountId);
    }

    public static Specification<Transfer> hasFromAccount(String accountNumber) {
        return (root, query, criteriaBuilder) ->
                accountNumber == null ? null :
                        criteriaBuilder.equal(root.get("fromAccount").get("accountNumber"), accountNumber);
    }

    // Filter by target account (toAccount) if provided
    public static Specification<Transfer> hasToAccount(String accountNumber) {
        return (root, query, criteriaBuilder) ->
                accountNumber == null ? null :
                        criteriaBuilder.equal(root.get("toAccount").get("accountNumber"), accountNumber);
    }

    public static Specification<Transfer> hasToAccount(Long toAccountId) {
        return (root, query, criteriaBuilder) ->
                toAccountId == null ? null :
                        criteriaBuilder.equal(root.get("toAccount").get("id"), toAccountId);
    }

    // Filter by minimum amount sent if provided
    public static Specification<Transfer> amountSentGreaterThanOrEqual(BigDecimal minAmountSent) {
        return (root, query, criteriaBuilder) ->
                minAmountSent == null ? null :
                        criteriaBuilder.greaterThanOrEqualTo(root.get("amountSent"), minAmountSent);
    }

    // Filter by maximum amount sent if provided
    public static Specification<Transfer> amountSentLessThanOrEqual(BigDecimal maxAmountSent) {
        return (root, query, criteriaBuilder) ->
                maxAmountSent == null ? null :
                        criteriaBuilder.lessThanOrEqualTo(root.get("amountSent"), maxAmountSent);
    }

    // Filter by transfer status if provided
    public static Specification<Transfer> hasTransferStatus(TransferStatus transferStatus) {
        return (root, query, criteriaBuilder) ->
                transferStatus == null ? null :
                        criteriaBuilder.equal(root.get("transferStatus"), transferStatus);
    }

    // Filter by timestamp after a certain date if provided
    public static Specification<Transfer> timestampAfter(LocalDateTime startDate) {
        return (root, query, criteriaBuilder) ->
                startDate == null ? null :
                        criteriaBuilder.greaterThanOrEqualTo(root.get("timestamp"), startDate);
    }

    // Filter by timestamp before a certain date if provided
    public static Specification<Transfer> timestampBefore(LocalDateTime endDate) {
        return (root, query, criteriaBuilder) ->
                endDate == null ? null :
                        criteriaBuilder.lessThanOrEqualTo(root.get("timestamp"), endDate);
    }

    // Filter by description if provided
    public static Specification<Transfer> hasDescription(String description) {
        return (root, query, criteriaBuilder) ->
                description == null ? null :
                        criteriaBuilder.like(root.get("description"), "%" + description + "%");
    }

    // Filter by from currency if provided
    public static Specification<Transfer> hasFromCurrency(String fromCurrency) {
        return (root, query, criteriaBuilder) ->
                fromCurrency == null ? null :
                        criteriaBuilder.equal(root.get("fromCurrency"), fromCurrency);
    }

    // Filter by to currency if provided
    public static Specification<Transfer> hasToCurrency(String toCurrency) {
        return (root, query, criteriaBuilder) ->
                toCurrency == null ? null :
                        criteriaBuilder.equal(root.get("toCurrency"), toCurrency);
    }

}

package com.jcmbeng.fundtransfer.utils;

import com.jcmbeng.fundtransfer.entities.Account;
import com.jcmbeng.fundtransfer.enums.AccountStatus;
import com.jcmbeng.fundtransfer.exceptions.AccountException;
import com.jcmbeng.fundtransfer.exceptions.ResourceNotFoundException;

import java.math.BigDecimal;

import static com.jcmbeng.fundtransfer.enums.CustomMessage.BALANCE_NOT_SUFFICIENT;
import static com.jcmbeng.fundtransfer.enums.CustomMessage.NON_ACTIVE_ACCOUNT;

public class AccountUtil {

    /**
     * Checks if the account is active.
     *
     * @param account the account to check.
     * @return true if the account is active, false otherwise.
     */
    public static boolean isActive(Account account) {
        return account != null && account.getAccountStatus () == AccountStatus.ACTIVE;
    }

    /**
     * Checks if the account has sufficient funds for a transaction.
     *
     * @param account the account to check.
     * @param amount the amount required.
     * @return true if the account has sufficient funds, false otherwise.
     */
    public static boolean hasSufficientFunds(Account account, BigDecimal amount) {
        return account != null && BigDecimalUtil.isGreaterThan (account.getTotalBalance (), amount);
    }

    /**
     * Calculates the usable balance for the account, based on the total balance
     * minus any funds held for pending or processing transfers.
     *
     * @param account the account to calculate for.
     * @pendingAmount All amounts in a status such as PENDING or PROCESSING.
     * @return the calculated usable balance.
     */
    public static BigDecimal calculateUsableBalance(Account account, BigDecimal pendingAmount) {

        return account.getTotalBalance ().subtract(pendingAmount != null ? pendingAmount : BigDecimal.ZERO);
    }

    /**
     * Validates if the account has the same currency as another account,
     * useful for transfer operations to check if a currency conversion is required.
     *
     * @param sourceAccount the source account.
     * @param targetAccount the target account.
     * @return true if both accounts have the same currency, false otherwise.
     */
    public static boolean hasSameCurrency(Account sourceAccount, Account targetAccount) {
        return sourceAccount != null && targetAccount != null
                && sourceAccount.getCurrency().equals(targetAccount.getCurrency());
    }

    /**
     * Checks if an account is eligible for withdrawal operations.
     * Only accounts with ACTIVE status and sufficient funds are eligible.
     *
     * @param account the account to check.
     * @param amount the withdrawal amount.
     * @return true if the account is eligible, false otherwise.
     */
    public static boolean canWithdraw(Account account, BigDecimal amount) {
        return isActive(account) && hasSufficientFunds(account, amount);
    }

    /**
     * Checks if an account is eligible for deposit operations.
     * Only accounts with ACTIVE status are eligible for deposits.
     *
     * @param account the account to check.
     * @return true if the account is eligible, false otherwise.
     */
    public static boolean canDeposit(Account account) {
        return isActive(account);
    }

    /**
     * Calculates and returns the total balance after a deposit operation.
     * Useful for simulating account balance before an actual database update.
     *
     * @param account the account to deposit into.
     * @param depositAmount the amount to deposit.
     * @return the new balance after deposit.
     */
    public static BigDecimal calculateBalanceAfterDeposit(Account account, BigDecimal depositAmount) {
        return account.getTotalBalance ().add(depositAmount != null ? depositAmount : BigDecimal.ZERO);
    }

    /**
     * Calculates and returns the total balance after a withdrawal operation.
     * Useful for simulating account balance before an actual database update.
     *
     * @param account the account to withdraw from.
     * @param withdrawalAmount the amount to withdraw.
     * @return the new balance after withdrawal, or null if insufficient funds.
     */
    public static BigDecimal calculateBalanceAfterWithdrawal(Account account, BigDecimal withdrawalAmount) {
        if (hasSufficientFunds(account, withdrawalAmount)) {
            return account.getTotalBalance().subtract(withdrawalAmount);
        } else {
            return null;  // Indicating insufficient funds
        }
    }

    public static void checkActivateStatus(Account account){
        if(!isActive (account)){
            new AccountException ("Unable to perform the action the account [ "+account.getAccountNumber ()+ "] " +
                    "status is " + account.getAccountStatus ().name (),
                    NON_ACTIVE_ACCOUNT);
        }
    }
}

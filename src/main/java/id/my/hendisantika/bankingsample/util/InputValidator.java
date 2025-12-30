package id.my.hendisantika.bankingsample.util;

import id.my.hendisantika.bankingsample.constants.constants;

/**
 * Created by IntelliJ IDEA.
 * Project : banking-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 30/12/25
 * Time: 07.53
 * To change this template use File | Settings | File Templates.
 */
public class InputValidator {
    public static boolean isSearchCriteriaValid(AccountInput accountInput) {
        return constants.SORT_CODE_PATTERN.matcher(accountInput.getSortCode()).find() &&
                constants.ACCOUNT_NUMBER_PATTERN.matcher(accountInput.getAccountNumber()).find();
    }

    public static boolean isAccountNoValid(String accountNo) {
        return constants.ACCOUNT_NUMBER_PATTERN.matcher(accountNo).find();
    }

    public static boolean isCreateAccountCriteriaValid(CreateAccountInput createAccountInput) {
        return (!createAccountInput.getBankName().isBlank() && !createAccountInput.getOwnerName().isBlank());
    }

    public static boolean isSearchTransactionValid(TransactionInput transactionInput) {
        // TODO Add checks for large amounts; consider past history of account holder and location of transfers

        if (!isSearchCriteriaValid(transactionInput.getSourceAccount()))
            return false;

        if (!isSearchCriteriaValid(transactionInput.getTargetAccount()))
            return false;

        return !transactionInput.getSourceAccount().equals(transactionInput.getTargetAccount());
    }
}

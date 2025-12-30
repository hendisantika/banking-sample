package id.my.hendisantika.bankingsample.util;

import com.mifmif.common.regex.Generex;

import static id.my.hendisantika.bankingsample.constants.constants.ACCOUNT_NUMBER_PATTERN_STRING;
import static id.my.hendisantika.bankingsample.constants.constants.SORT_CODE_PATTERN_STRING;

/**
 * Created by IntelliJ IDEA.
 * Project : banking-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 30/12/25
 * Time: 07.52
 * To change this template use File | Settings | File Templates.
 */
public class CodeGenerator {
    Generex sortCodeGenerex = new Generex(SORT_CODE_PATTERN_STRING);
    Generex accountNumberGenerex = new Generex(ACCOUNT_NUMBER_PATTERN_STRING);

    public CodeGenerator() {
    }

    public String generateSortCode() {
        return sortCodeGenerex.random();
    }

    public String generateAccountNumber() {
        return accountNumberGenerex.random();
    }
}

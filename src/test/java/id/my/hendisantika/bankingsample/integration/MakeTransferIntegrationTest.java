package id.my.hendisantika.bankingsample.integration;

import id.my.hendisantika.bankingsample.controller.TransactionRestController;
import id.my.hendisantika.bankingsample.util.AccountInput;
import id.my.hendisantika.bankingsample.util.TransactionInput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Project : banking-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 30/12/25
 * Time: 18.57
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "local")
class MakeTransferIntegrationTest {

    @Autowired
    private TransactionRestController transactionRestController;

    @Test
    void givenTransactionDetails_whenMakeTransaction_thenVerifyTransactionIsProcessed() {
        // given
        var sourceAccount = new AccountInput();
        sourceAccount.setSortCode("53-68-92");
        sourceAccount.setAccountNumber("73084635");

        var targetAccount = new AccountInput();
        targetAccount.setSortCode("65-93-37");
        targetAccount.setAccountNumber("21956204");

        var input = new TransactionInput();
        input.setSourceAccount(sourceAccount);
        input.setTargetAccount(targetAccount);
        input.setAmount(27.5);
        input.setReference("My reference");
        input.setLatitude(45.0000000);
        input.setLongitude(90.0000000);

        // when
        var body = transactionRestController.makeTransfer(input).getBody();

        // then
        var isComplete = (Boolean) body;
        assertThat(isComplete).isTrue();
    }
}

package id.my.hendisantika.bankingsample.unit;

import id.my.hendisantika.bankingsample.model.Account;
import id.my.hendisantika.bankingsample.repository.AccountRepository;
import id.my.hendisantika.bankingsample.repository.TransactionRepository;
import id.my.hendisantika.bankingsample.service.TransactionService;
import id.my.hendisantika.bankingsample.util.AccountInput;
import id.my.hendisantika.bankingsample.util.TransactionInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * Project : banking-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 30/12/25
 * Time: 19.00
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest
class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;
    @MockitoBean
    private AccountRepository accountRepository;
    @MockitoBean
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        var sourceAccount = new Account(1L, "53-68-92", "78901234", 458.1, "Some Bank", "John");
        var targetAccount = new Account(2L, "67-41-18", "48573590", 64.9, "Some Other Bank", "Major");

        when(accountRepository.findBySortCodeAndAccountNumber("53-68-92", "78901234"))
                .thenReturn(Optional.of(sourceAccount));
        when(accountRepository.findBySortCodeAndAccountNumber("67-41-18", "48573590"))
                .thenReturn(Optional.of(targetAccount));
    }

    @Test
    void whenTransactionDetails_thenTransferShouldBeDenied() {
        var sourceAccount = new AccountInput();
        sourceAccount.setSortCode("53-68-92");
        sourceAccount.setAccountNumber("78901234");

        var targetAccount = new AccountInput();
        targetAccount.setSortCode("67-41-18");
        targetAccount.setAccountNumber("48573590");

        var input = new TransactionInput();
        input.setSourceAccount(sourceAccount);
        input.setTargetAccount(targetAccount);
        input.setAmount(50);
        input.setReference("My reference");

        boolean isComplete = transactionService.makeTransfer(input);

        assertThat(isComplete).isTrue();
    }

    @Test
    void whenTransactionDetailsAndAmountTooLarge_thenTransferShouldBeDenied() {
        var sourceAccount = new AccountInput();
        sourceAccount.setSortCode("53-68-92");
        sourceAccount.setAccountNumber("78901234");

        var targetAccount = new AccountInput();
        targetAccount.setSortCode("67-41-18");
        targetAccount.setAccountNumber("48573590");

        var input = new TransactionInput();
        input.setSourceAccount(sourceAccount);
        input.setTargetAccount(targetAccount);
        input.setAmount(10000);
        input.setReference("My reference");

        boolean isComplete = transactionService.makeTransfer(input);

        assertThat(isComplete).isFalse();
    }
}

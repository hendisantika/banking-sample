package id.my.hendisantika.bankingsample.repository;

import id.my.hendisantika.bankingsample.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : banking-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 30/12/25
 * Time: 07.48
 * To change this template use File | Settings | File Templates.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findBySortCodeAndAccountNumber(String sortCode, String accountNumber);

    Optional<Account> findByAccountNumber(String accountNumber);
}

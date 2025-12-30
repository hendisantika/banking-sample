package id.my.hendisantika.bankingsample.unit;

import id.my.hendisantika.bankingsample.model.Account;
import id.my.hendisantika.bankingsample.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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
@SpringBootTest
@AutoConfigureMockMvc
class AccountRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private AccountService accountService;

    @Test
    void givenMissingInput_whenCheckingBalance_thenVerifyBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void givenInvalidInput_whenCheckingBalance_thenVerifyBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/accounts")
                        .content("{\"sortCode\": \"53-68\",\"accountNumber\": \"78934\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void givenNoAccountForInput_whenCheckingBalance_thenVerifyOkWithMessage() throws Exception {
        when(accountService.getAccount(anyString(), anyString())).thenReturn(null);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/accounts")
                        .content("{\"sortCode\": \"53-68-92\",\"accountNumber\": \"78901234\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void givenAccountDetails_whenCheckingBalance_thenVerifyOk() throws Exception {
        when(accountService.getAccount(anyString(), anyString())).thenReturn(
                new Account(1L, "53-68-92", "78901234", 10.1, "Some Bank", "John"));

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/accounts")
                        .content("{\"sortCode\": \"53-68-92\",\"accountNumber\": \"78901234\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}

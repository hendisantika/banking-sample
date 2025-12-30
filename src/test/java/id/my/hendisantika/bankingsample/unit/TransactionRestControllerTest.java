package id.my.hendisantika.bankingsample.unit;

import id.my.hendisantika.bankingsample.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created by IntelliJ IDEA.
 * Project : banking-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 30/12/25
 * Time: 18.59
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest
@AutoConfigureMockMvc
class TransactionRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private TransactionService transactionService;

    @Test
    void givenMissingInput_whenMakeTransfer_thenVerifyBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/transactions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void givenInvalidInput_whenMakeTransfer_thenVerifyBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/transactions")
                        .content("{ \"sourceAccount\": {\"sortCode\": \"53-68-92\", \"accountNumber\": \"73084635\" }, \"targetAccount\": {\"sortCode\": \"65-93-37\", \"accountNumber\": \"21956204\"}, \"amount\": -10}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void givenNoAccountForInput_whenMakeTransfer_thenVerifyOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/transactions")
                        .content("{\"sourceAccount\": {\"sortCode\": \"53-68-92\", \"accountNumber\": \"73084635\"}, \"targetAccount\": {\"sortCode\": \"65-93-37\", \"accountNumber\": \"21956204\"}, \"amount\": 105.0, \"reference\": \"My ref\", \"latitude\": 66.23423423, \"longitude\": 105.234234}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

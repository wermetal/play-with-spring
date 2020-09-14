package pm.provider.slot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pm.provider.integration.parimatch.player.PmPlayerInfo;
import pm.provider.player.PlayerService;


@WebMvcTest(controllers = SlotController.class)
public class SlotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SlotService slotService;

    @MockBean
    private PlayerService playerService;


    @Test
    void whenValidInput_thenReturns200() throws Exception {
        PmPlayerInfo mockedPlayerInfo = new PmPlayerInfo("player1", 2000L, "UAH", "UA");
        String sessionId = "sessionId";
        SlotBetRequest betRequest = new SlotBetRequest(1000L);
        SpinResult spinResult = new SpinResult("round1", 300L, 1300L);

        when(playerService.getPlayerInfo(any())).thenReturn(mockedPlayerInfo);
        when(slotService.spin(
                mockedPlayerInfo,
                sessionId,
                betRequest.getBet()
        )).thenReturn(spinResult);

        mockMvc.perform(post("/slot/spin")
                .contentType("application/json")
                .header("Provider-Session-Id", sessionId)
                .content(objectMapper.writeValueAsString(betRequest)))
                .andExpect(status().isOk());

    }

}
package ua.golubenko.slots.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ua.golubenko.slots.domain.SlotService;
import ua.golubenko.slots.domain.client.model.player.PmPlayerInfo;
import ua.golubenko.slots.domain.PlayerService;
import ua.golubenko.slots.web.SlotController;
import ua.golubenko.slots.web.model.slot.SlotBetRequest;
import ua.golubenko.slots.web.model.slot.SpinResult;


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
    void spin_whenNullValue_thenReturns400() throws Exception {
        String sessionId = "sessionId";
        SlotBetRequest betRequest = new SlotBetRequest(null);

        mockMvc.perform(post("/slot/spin")
                .contentType("application/json")
                .header("Provider-Session-Id", sessionId)
                .content(objectMapper.writeValueAsString(betRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void spin_whenValidInput_thenMapsToBusinessModel() throws Exception {
        String sessionId = "sessionId";
        SlotBetRequest betRequest = new SlotBetRequest(1000L);

        PmPlayerInfo mockedPlayerInfo = new PmPlayerInfo("player1", 2000L, "UAH", "UA");
        when(playerService.getPlayerInfo(any())).thenReturn(mockedPlayerInfo);

        mockMvc.perform(post("/slot/spin")
                .contentType("application/json")
                .header("Provider-Session-Id", sessionId)
                .content(objectMapper.writeValueAsString(betRequest)))
                .andExpect(status().isOk());

        verify(playerService, times(1)).getPlayerInfo(eq(sessionId));
        verify(slotService, times(1)).spin(
                eq(mockedPlayerInfo),
                eq(sessionId),
                eq(betRequest.getBet())
        );
    }

    @Test
    void spin_whenValidInput_thenReturnsSpinResult() throws Exception {
        String sessionId = "sessionId";
        SlotBetRequest betRequest = new SlotBetRequest(1000L);

        SpinResult spinResult = new SpinResult("round1", 300L, 1300L);
        when(slotService.spin(any(), any(), any())).thenReturn(spinResult);

        MvcResult mvcResult = mockMvc.perform(post("/slot/spin")
                .contentType("application/json")
                .header("Provider-Session-Id", sessionId)
                .content(objectMapper.writeValueAsString(betRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(spinResult));

    }

}

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
import ua.golubenko.slots.domain.PlayerService;
import ua.golubenko.slots.domain.client.model.player.PmPlayerInfo;
import ua.golubenko.slots.web.model.LoginData;
import ua.golubenko.slots.web.PlayerController;


@WebMvcTest(controllers = PlayerController.class)
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlayerService playerService;

    @Test
    void login_whenNullValue_thenReturns400() throws Exception {
        LoginData loginData = new LoginData(null, null);

        mockMvc.perform(post("/player/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(loginData)))
                .andExpect(status().isOk());
    }

    @Test
    void login_whenValidInput_thenMapsToBusinessModel() throws Exception {
        String mockedSessionId = "session-string";
        LoginData loginData = new LoginData("player1", "password");

        when(playerService.login(any(), any())).thenReturn(mockedSessionId);

        MvcResult mvcResult = mockMvc.perform(post("/player/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(loginData)))
                .andExpect(status().isOk())
                .andReturn();

        verify(playerService, times(1)).login(
                eq(loginData.getLogin()),
                eq(loginData.getPassword())
        );

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(mockedSessionId);
    }

    @Test
    void playerInfo_whenInvalidSessionId_thenReturns400() throws Exception{
        mockMvc.perform(get("/player/info")
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void playerInfo_whenValidInput_thenReturnPlayerInfo() throws Exception {
        String mockedSessionId = "session-string";

        PmPlayerInfo playerInfo = new PmPlayerInfo(
                "player_id_1",
                2000L,
                "UAH",
                "UA"
        );

        when(playerService.getPlayerInfo(any())).thenReturn(playerInfo);

        MvcResult mvcResult = mockMvc.perform(get("/player/info")
                .contentType("application/json")
                .header("Provider-Session-Id", mockedSessionId))
                .andExpect(status().isOk())
                .andReturn();

        verify(playerService, times(1)).getPlayerInfo(
                eq(mockedSessionId)
        );

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(playerInfo)
        );
    }

}

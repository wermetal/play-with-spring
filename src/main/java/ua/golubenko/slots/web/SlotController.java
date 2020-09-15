package ua.golubenko.slots.web;

import org.springframework.web.bind.annotation.*;
import ua.golubenko.slots.domain.client.model.player.PmPlayerInfo;
import ua.golubenko.slots.domain.PlayerService;
import ua.golubenko.slots.web.model.slot.SlotBetRequest;
import ua.golubenko.slots.domain.SlotService;
import ua.golubenko.slots.web.model.slot.SpinResult;

import javax.validation.Valid;

@RestController
public class SlotController {

    PlayerService playerService;
    SlotService slotService;
    SlotController(
            PlayerService playerService,
            SlotService slotService
    ) {
        this.playerService = playerService;
        this.slotService = slotService;
    }

    @PostMapping("/slot/spin")
    @ResponseBody
    SpinResult spin(
            @RequestHeader("Provider-Session-Id") String sessionId,
            @Valid @RequestBody SlotBetRequest betRequest
    ) {
        PmPlayerInfo playerInfo = this.playerService.getPlayerInfo(sessionId);
        return this.slotService.spin(playerInfo, sessionId, betRequest.getBet());
    }
}

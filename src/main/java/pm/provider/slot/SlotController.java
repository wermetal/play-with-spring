package pm.provider.slot;

import org.springframework.web.bind.annotation.*;
import pm.provider.integration.parimatch.player.PmPlayerInfo;
import pm.provider.player.PlayerService;

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
            @RequestBody SlotBetRequest betRequest
    ) {
        PmPlayerInfo playerInfo = this.playerService.getPlayerInfo(sessionId);
        return this.slotService.spin(playerInfo, sessionId, betRequest.getBet());
    }
}

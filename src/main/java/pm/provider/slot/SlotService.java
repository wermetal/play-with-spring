package pm.provider.slot;

import org.springframework.stereotype.Service;
import pm.provider.integration.parimatch.PmIntegrationService;
import pm.provider.integration.parimatch.bet.PmBetInfo;
import pm.provider.integration.parimatch.player.PmPlayerInfo;

import java.util.Random;

@Service
public class SlotService {
    private PmIntegrationService integrationService;

    SlotService(
            PmIntegrationService integrationService
    ) {
        this.integrationService = integrationService;
    }

    public SpinResult spin(PmPlayerInfo playerInfo, String sessionId, Long bet) {
        String roundId = getRoundId();

        String mockSessionToken = "valid-session-token";
        PmBetInfo betInfo = integrationService.makeBet(
                "42",
                roundId,
                getProductId(),
                10000L,
                mockSessionToken,
                playerInfo
        );
        Long winAmount = this.getRNGResult(bet);


        SpinResult result = new SpinResult();
        result.setRoundId(roundId);

        if (winAmount > 0) {
            Long mockWinAmount = 2000L;
            PmBetInfo winInfo = integrationService.setWin(
                    "43",
                    roundId,
                    getProductId(),
                    mockWinAmount,
                    mockSessionToken,
                    playerInfo
            );
            result.setBalance(winInfo.getBalance());
            result.setWinAmount(mockWinAmount);
        } else {
            result.setBalance(betInfo.getBalance());
            result.setWinAmount(winAmount);
        }
        return result;
    }

    private String getRoundId() {
        return "round-id-1";
    }

    private String getProductId() {
        return "cool-slot-game";
    }


    private Long getRNGResult(Long bet) {
        Random rand = new Random();
        int win = rand.nextInt(10);
        if (win < 5) {
            return bet * (win + 1);
        }
        return 0L;
    }
}

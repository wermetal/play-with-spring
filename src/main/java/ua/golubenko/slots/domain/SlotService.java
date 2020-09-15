package ua.golubenko.slots.domain;

import org.springframework.stereotype.Service;
import ua.golubenko.slots.domain.client.PmIntegrationService;
import ua.golubenko.slots.domain.client.model.bet.PmBetInfo;
import ua.golubenko.slots.domain.client.model.player.PmPlayerInfo;
import ua.golubenko.slots.web.model.slot.SpinResult;

import java.util.Random;
import java.util.UUID;

@Service
public class SlotService {
    private PmIntegrationService integrationService;
    private Long txId = 0L;

    SlotService(
            PmIntegrationService integrationService
    ) {
        this.integrationService = integrationService;
    }

    public SpinResult spin( PmPlayerInfo playerInfo, String sessionId, Long bet) {
        String roundId = getRoundId();

        PmBetInfo betInfo = integrationService.makeBet(
                getTxId(),
                roundId,
                getProductId(),
                bet,
                sessionId,
                playerInfo
        );
        Long winAmount = this.getRNGResult(bet);


        SpinResult result = new SpinResult();
        result.setRoundId(roundId);

        if (winAmount > 0) {
            PmBetInfo winInfo = integrationService.setWin(
                    getTxId(),
                    roundId,
                    getProductId(),
                    winAmount,
                    sessionId,
                    playerInfo
            );
            result.setBalance(winInfo.getBalance());
        } else {
            result.setBalance(betInfo.getBalance());
        }

        result.setWinAmount(winAmount);
        return result;
    }

    private String getRoundId() {
        return UUID.randomUUID().toString();
    }

    private String getTxId() {
        txId += 1;
        return txId.toString();
    }

    private String getProductId() {
        return "new-awesome-mega-slot";
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

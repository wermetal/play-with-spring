package ua.golubenko.slots.domain.client;

import org.springframework.stereotype.Service;
import ua.golubenko.slots.domain.client.model.bet.PmBetInfoRequest;
import ua.golubenko.slots.domain.client.model.player.PmPlayerInfoRequest;

@Service
public class PactTransformer {

    public static PmPlayerInfoRequest transformRequestForPlayerInfo( PmPlayerInfoRequest req) {
        PmPlayerInfoRequest request = new PmPlayerInfoRequest();
        request.setCid("parimatch");
        request.setSessionToken("valid-session-token");
        return request;
    }


    public static PmBetInfoRequest transformRequestMakeBet(PmBetInfoRequest req) {
        PmBetInfoRequest request = new PmBetInfoRequest();
        request.setCid("parimatch");
        request.setSessionToken("valid-session-token");
        request.setPlayerId("john");
        request.setProductId("cool-slot-game");
        request.setRoundId("round-id-1");
        request.setTxId("42");
        request.setAmount(1000L);
        request.setCurrency("EUR");
        request.setRoundClosed(false);
        return request;
    }

    public  static PmBetInfoRequest transformRequestSetWin(PmBetInfoRequest req) {
        PmBetInfoRequest request = new PmBetInfoRequest();
        request.setCid("parimatch");
        request.setSessionToken("valid-session-token");
        request.setPlayerId("john");
        request.setProductId("cool-slot-game");
        request.setRoundId("round-id-1");
        request.setTxId("43");
        request.setAmount(2000L);
        request.setCurrency("EUR");
        request.setRoundClosed(false);
        return request;
    }

}

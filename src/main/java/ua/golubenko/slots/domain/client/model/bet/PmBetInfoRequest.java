package ua.golubenko.slots.domain.client.model.bet;

import lombok.Data;

@Data
public class PmBetInfoRequest {

    private String cid;
    private String sessionToken;
    private String playerId;
    private String productId;
    private String roundId;
    private String txId;
    private Long amount;
    private String currency;
    private boolean roundClosed;
}

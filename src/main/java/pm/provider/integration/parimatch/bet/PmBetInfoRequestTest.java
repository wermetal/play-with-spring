package pm.provider.integration.parimatch.bet;

import lombok.Data;

@Data
public class PmBetInfoRequestTest {
    private String cid;
    private String sessionToken;
    private String playerId;
    private String productId;
    private String roundId;
    private String txId;
    private Long amount;
    private String currency;
    private boolean roundClosed;

    public PmBetInfoRequestTest() { }
}

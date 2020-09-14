package pm.provider.integration.parimatch.player;

import lombok.Data;

@Data
public class PmPlayerInfo {
    private String country;
    private Long balance;
    private String currency;
    private String playerId;

    PmPlayerInfo() {}

    public static PmPlayerInfo fromResponse(PmPlayerInfoResponse response) {
        PmPlayerInfo playerInfo = new PmPlayerInfo();
        playerInfo.country = response.getCountry();
        playerInfo.balance = response.getBalance();
        playerInfo.currency = response.getCurrency();
        playerInfo.playerId = response.getPlayerId();
        return playerInfo;
    }
}

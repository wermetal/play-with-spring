package ua.golubenko.slots.domain.client.model.player;

import lombok.Data;

@Data
public class PmPlayerInfo {

    private String country;
    private Long balance;
    private String currency;
    private String playerId;

    public PmPlayerInfo() {}

    public PmPlayerInfo(String playerId, Long balance, String currency, String country) {
        this.country = country;
        this.balance = balance;
        this.currency = currency;
        this.playerId = playerId;
    }

    public static PmPlayerInfo fromResponse(PmPlayerInfoResponse response) {
        return new PmPlayerInfo(
                response.getPlayerId(),
                response.getBalance(),
                response.getCurrency(),
                response.getCountry()
        );
    }
}

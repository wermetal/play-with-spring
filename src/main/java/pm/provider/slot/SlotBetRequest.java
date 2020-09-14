package pm.provider.slot;

import lombok.Data;

@Data
public class SlotBetRequest {
    private Long bet;

    public SlotBetRequest() {}

    public SlotBetRequest(Long bet) {
        this.bet = bet;
    }
}

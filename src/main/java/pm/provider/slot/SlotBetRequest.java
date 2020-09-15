package pm.provider.slot;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class SlotBetRequest {

    @NotNull
    private Long bet;

    public SlotBetRequest() {}

    public SlotBetRequest(Long bet) {
        this.bet = bet;
    }
}

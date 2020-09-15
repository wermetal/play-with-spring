package ua.golubenko.slots.web.model.slot;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class SlotBetRequest {

    @NotNull
    @Min(1)
    private Long bet;

    public SlotBetRequest() {}

    public SlotBetRequest(Long bet) {
        this.bet = bet;
    }
}

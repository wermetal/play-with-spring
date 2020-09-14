package pm.provider.slot;

import lombok.Data;

@Data
public class SpinResult {
    private String roundId;
    private Long winAmount;
    private Long balance;

    public SpinResult() {
    }

    public SpinResult(String roundId, Long winAmount, Long balance) {
        this.roundId = roundId;
        this.winAmount = winAmount;
        this.balance = balance;
    }
}

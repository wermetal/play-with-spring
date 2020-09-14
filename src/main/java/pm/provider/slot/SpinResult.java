package pm.provider.slot;

import lombok.Data;

@Data
public class SpinResult {
    private Long winAmount;
    private Long balance;
    private String roundId;

    SpinResult() {
    }
}

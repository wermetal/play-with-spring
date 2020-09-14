package pm.provider.integration.parimatch.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmPlayerInfoResponse {
    private String country;
    private Long balance;
    private String currency;
    private String playerId;

    PmPlayerInfoResponse() {}
}

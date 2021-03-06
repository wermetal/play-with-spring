package ua.golubenko.slots.domain.client.model.bet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmBetInfoResponse {

    private String createdAt;
    private Long balance;
    private String txId;
    private String processedTxId;
    private boolean alreadyProcessed;
}

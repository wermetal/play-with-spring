package ua.golubenko.slots.domain.client.model.bet;

import lombok.Data;

@Data
public class PmBetInfo {

    private String createdAt;
    private Long balance;
    private String txId;
    private String processedTxId;
    private boolean alreadyProcessed;

    PmBetInfo() {}


    public static PmBetInfo fromResponse(PmBetInfoResponse response) {
        PmBetInfo data = new PmBetInfo();
        data.setCreatedAt(response.getCreatedAt());
        data.setBalance(response.getBalance());
        data.setTxId(response.getTxId());
        data.setProcessedTxId(response.getProcessedTxId());
        data.setAlreadyProcessed(response.isAlreadyProcessed());
        return data;
    }
}

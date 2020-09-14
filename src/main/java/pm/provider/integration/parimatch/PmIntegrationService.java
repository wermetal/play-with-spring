package pm.provider.integration.parimatch;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pm.provider.integration.parimatch.bet.PmBetInfo;
import pm.provider.integration.parimatch.bet.PmBetInfoRequestTest;
import pm.provider.integration.parimatch.bet.PmBetInfoResponse;
import pm.provider.integration.parimatch.player.PmPlayerInfo;
import pm.provider.integration.parimatch.player.PmPlayerInfoRequest;
import pm.provider.integration.parimatch.player.PmPlayerInfoResponse;

import java.util.Collections;

@Service
public class PmIntegrationService {
    private String AUTH = "YmV0bGFiOnBhc3N3b3Jk";
    private String EvaHubCoumsumer = "Oz";
    private String EvaProductType = "slots";
    private String BASE_URL = "http://localhost:8081/s/hub/";
    private String cid = "parimatch";

    private RestTemplate restTemplate;

    PmIntegrationService(
            RestTemplateBuilder restTemplateBuilder
    ) {
        this.restTemplate = restTemplateBuilder
                .errorHandler(new PmResponseErrorHandler())
                .build();
    }

    public PmPlayerInfo getPlayerInfo(String sessionToken) {
        PmPlayerInfoRequest playerInfoRequest = new PmPlayerInfoRequest(cid, sessionToken);
        ResponseEntity<PmPlayerInfoResponse> response = this.restTemplate.exchange(
                BASE_URL + "slots/playerInfo",
                HttpMethod.POST,
                new HttpEntity<Object>(playerInfoRequest, getHeaders()),
                PmPlayerInfoResponse.class
        );

        return PmPlayerInfo.fromResponse(response.getBody());
    }

    public PmBetInfo makeBet(
            String txId,
            String roundId,
            String productId,
            Long amount,
            String sessionToken,
            PmPlayerInfo playerInfo
    ) {
        PmBetInfoRequestTest betRequest = new PmBetInfoRequestTest();
        betRequest.setCid(cid);
        betRequest.setSessionToken(sessionToken);
        betRequest.setProductId(productId);
        betRequest.setRoundId(roundId);
        betRequest.setTxId(txId);
        betRequest.setAmount(amount);

        betRequest.setPlayerId(playerInfo.getPlayerId());
        betRequest.setCurrency(playerInfo.getCurrency());
        betRequest.setRoundClosed(false);

        HttpEntity<Object> entity = new HttpEntity<>(betRequest, getHeaders());
        ResponseEntity<PmBetInfoResponse> response = this.restTemplate.exchange(
                BASE_URL + "slots/bet",
                HttpMethod.POST,
                entity,
                PmBetInfoResponse.class
        );

        return PmBetInfo.fromResponse(response.getBody());
    }


    public PmBetInfo setWin(
            String txId,
            String roundId,
            String productId,
            Long amount,
            String sessionToken,
            PmPlayerInfo playerInfo
    ) {
        PmBetInfoRequestTest betRequest = new PmBetInfoRequestTest();
        betRequest.setCid(cid);
        betRequest.setSessionToken(sessionToken);
        betRequest.setProductId(productId);
        betRequest.setRoundId(roundId);
        betRequest.setTxId(txId);
        betRequest.setAmount(amount);

        betRequest.setPlayerId(playerInfo.getPlayerId());
        betRequest.setCurrency(playerInfo.getCurrency());
        betRequest.setRoundClosed(false);

        ResponseEntity<PmBetInfoResponse> response = this.restTemplate.exchange(
                BASE_URL + "slots/win",
                HttpMethod.POST,
                new HttpEntity<Object>(betRequest, getHeaders()),
                PmBetInfoResponse.class
        );

        return PmBetInfo.fromResponse(response.getBody());
    }


    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(AUTH);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Eva-Hub-Consumer", EvaHubCoumsumer);
        headers.add("Eva-Product-Type", EvaProductType);
        return headers;
    }
}

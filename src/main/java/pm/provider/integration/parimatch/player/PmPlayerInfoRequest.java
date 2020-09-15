package pm.provider.integration.parimatch.player;

import lombok.Data;

@Data
public class PmPlayerInfoRequest {
    private String cid;
    private String sessionToken;


    public PmPlayerInfoRequest () {}

    public PmPlayerInfoRequest(String cid, String sessionToken) {
        this.cid = cid;
        this.sessionToken = sessionToken;
    }
}

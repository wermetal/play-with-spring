package pm.provider.integration.parimatch;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class PmResponseErrorHandler
        implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {

        return (
                httpResponse.getStatusCode().series() == CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        PmErrorResponse error = objectMapper.readValue(httpResponse.getBody(), PmErrorResponse.class);

        switch (error.getCode()) {
            case "error.client.web.invalid.session-key":
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Session Token");
            default:
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unhandled Exception");
        }
    }
}



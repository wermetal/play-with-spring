package ua.golubenko.slots.domain.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.server.ResponseStatusException;
import ua.golubenko.slots.domain.client.model.PmErrorResponse;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class PmResponseErrorHandler
        implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {

        HttpStatus status = httpResponse.getStatusCode();
        return (
                status.series() == CLIENT_ERROR
                        || status.series() == SERVER_ERROR);
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



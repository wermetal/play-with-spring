package ua.golubenko.slots.domain.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PmErrorResponse {

    private String code;
    private String date;
    private String origin;
    private String message;
}

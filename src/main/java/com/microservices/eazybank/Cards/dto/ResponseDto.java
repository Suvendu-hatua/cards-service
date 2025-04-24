package com.microservices.eazybank.Cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold Successful response information"
)
public class ResponseDto {
    @Schema(
            description = "Status code in the response"
    )
    private String httpStatus;

    @Schema(
            description = "Status message in the response"
    )
    private String message;
}

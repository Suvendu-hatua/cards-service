package com.microservices.eazybank.Cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold ErrorResponse information"
)
public class ErrorResponseDto {

    @Schema(
            description ="API path invoked by client"
    )
    private String apiPath;

    @Schema(
            description = "Error code representing the error happened"
    )
    private HttpStatus httpStatus;

    @Schema(
            description = "Error message representing the error happened"
    )
    private String message;

    @Schema(
            description = "Time representing when the error happened"
    )
    private LocalDateTime timestamp;
}

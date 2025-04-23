package com.microservices.eazybank.Cards.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ErrorResponseDto {

    private String apiPath;

    private HttpStatus httpStatus;

    private String message;

    private LocalDateTime timestamp;
}

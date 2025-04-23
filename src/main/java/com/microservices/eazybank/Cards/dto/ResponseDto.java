package com.microservices.eazybank.Cards.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class ResponseDto {
    private String httpStatus;
    private String message;
}

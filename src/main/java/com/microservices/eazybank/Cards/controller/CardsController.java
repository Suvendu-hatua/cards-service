package com.microservices.eazybank.Cards.controller;

import com.microservices.eazybank.Cards.constants.CardsConstants;
import com.microservices.eazybank.Cards.dto.CardsDto;
import com.microservices.eazybank.Cards.dto.ErrorResponseDto;
import com.microservices.eazybank.Cards.dto.ResponseDto;
import com.microservices.eazybank.Cards.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cards")
@Slf4j
@Tag(
        name = "CRUD REST APIs for Cards for Banking MicroService",
        description = "CRUD REST APIs for Banking MicroServices to CREATE, UPDATE, FETCH AND DELETE card details"
)
public class CardsController {
    private CardService cardService;

    @Autowired
    public CardsController(CardService cardService) {
        this.cardService = cardService;
    }

    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create new Card in Banking Microservice"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "201",
                            description = "HTTP Status CREATED",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@RequestParam String mobileNumber) {
        cardService.createCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(CardsConstants.STATUS_201,CardsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Card Details REST API",
            description = "REST API to fetch card details based on a mobile number"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP Status OK",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CardsDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server Error",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )
    @GetMapping("/fetch")
    public  ResponseEntity<CardsDto> fetchCardDetails(@RequestParam String mobileNumber) {
        CardsDto cardsDto = cardService.fetchCardDetails(mobileNumber);
        log.info("Fetched card details: {}", cardsDto);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @Operation(
            summary = "Update Card Details REST API",
            description = "REST API to update card details based on a card number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CardsDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCardDeatils( CardsDto cardsDto) {
        boolean isUpdated = cardService.updateCardDetails(cardsDto);
        if(isUpdated) {
            log.info("Card details Updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardsConstants.STATUS_200,CardsConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardsConstants.STATUS_417,CardsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Card Details REST API",
            description = "REST API to delete Card details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCardDetails(@RequestParam String mobileNumber) {
        boolean isDeleted = cardService.deleteCardDetails(mobileNumber);
        if(isDeleted) {
            log.info("Card deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardsConstants.STATUS_200,CardsConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(CardsConstants.STATUS_417,CardsConstants.MESSAGE_417_DELETE));
        }
    }


}

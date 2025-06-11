package com.aws.person.infraestructure.imput.rest;

import com.aws.person.application.dto.*;
import com.aws.person.application.handler.IPersonHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/person/")
public class PersonRestController {

    private final IPersonHandler personHandler;

    public PersonRestController(IPersonHandler personHandler) {
        this.personHandler = personHandler;
    }

    @Operation(summary = "Get a rol by Email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonResponse.class))),
            @ApiResponse(responseCode = "404", description = "Owner not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> getPersonById(@Parameter(description = "Number of the user to be returned")
                                                       @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(personHandler.getPersonById(id));
    }

    @Operation(summary = "Add a new person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Person already exists", content = @Content)
    })
    @PostMapping("/create/")
    public ResponseEntity<?> savePerson(@RequestBody @Valid PersonRequest personRequest) {
        personHandler.savePerson(personRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

 

   

}

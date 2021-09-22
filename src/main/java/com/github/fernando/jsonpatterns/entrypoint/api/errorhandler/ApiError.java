package com.github.fernando.jsonpatterns.entrypoint.api.errorhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
class ApiError {

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Error> errors;

}
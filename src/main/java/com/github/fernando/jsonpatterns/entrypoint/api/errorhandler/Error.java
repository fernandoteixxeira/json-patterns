package com.github.fernando.jsonpatterns.entrypoint.api.errorhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
class Error {

    private String scope;
    private String field;
    private String value;
    private String message;

}
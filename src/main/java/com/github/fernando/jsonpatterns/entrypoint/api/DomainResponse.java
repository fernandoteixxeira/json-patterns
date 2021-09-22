package com.github.fernando.jsonpatterns.entrypoint.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
class DomainResponse {

    private Integer domainId;
    private String domainDescription;
    private String domainColor;
    @JsonProperty("domainDate")
    private LocalDate domainDate;
    private LocalDateTime domainTime;

}

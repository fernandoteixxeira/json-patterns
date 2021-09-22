package com.github.fernando.jsonpatterns.entrypoint.api.camelcase;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
class DomainCamelCaseResponse {

    private Integer domainId;
    private String domainDescription;
    private String domainColor;
    @JsonProperty("domain_date")
    private LocalDate domainDate;
    private LocalDateTime domainTime;

}

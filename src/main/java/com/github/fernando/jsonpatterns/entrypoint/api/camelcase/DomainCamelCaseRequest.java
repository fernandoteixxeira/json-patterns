package com.github.fernando.jsonpatterns.entrypoint.api.camelcase;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@ToString
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
class DomainCamelCaseRequest {

    @NotNull
    @Max(999)
    private Integer domainId;
    @NotBlank
    @Size(max = 100)
    private String domainDescription;
    @JsonProperty("domain_date")
    private LocalDate domainDate;
    private LocalDateTime domainTime;

}

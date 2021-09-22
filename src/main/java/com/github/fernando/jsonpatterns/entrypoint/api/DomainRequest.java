package com.github.fernando.jsonpatterns.entrypoint.api;

import com.fasterxml.jackson.annotation.JsonProperty;
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
class DomainRequest {

    @NotNull
    @Max(999)
    private Integer domainId;
    @NotBlank
    @Size(max = 100)
    private String domainDescription;
    @JsonProperty("domainDate")
    private LocalDate domainDate;
    private LocalDateTime domainTime;

}

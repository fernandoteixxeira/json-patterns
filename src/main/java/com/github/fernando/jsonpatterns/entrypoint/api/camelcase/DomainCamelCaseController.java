package com.github.fernando.jsonpatterns.entrypoint.api.camelcase;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Validated
@RestController
@RequestMapping("/camel-case/domain")
@AllArgsConstructor
@Slf4j
class DomainCamelCaseController {

    private static final Faker FAKER = Faker.instance();

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DomainCamelCaseResponse getById(@PathVariable("id") @Max(999) final Integer id) {
        return DomainCamelCaseResponse.builder()
            .domainId(id)
            .domainDescription(FAKER.company().name())
            .domainColor(FAKER.color().hex())
            .domainDate(LocalDate.now())
            .domainTime(LocalDateTime.now())
            .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@Valid @RequestBody final DomainCamelCaseRequest domainRequest) {
        log.info(domainRequest.toString());
    }

}

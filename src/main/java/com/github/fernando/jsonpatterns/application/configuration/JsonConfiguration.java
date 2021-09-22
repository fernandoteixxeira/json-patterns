package com.github.fernando.jsonpatterns.application.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class JsonConfiguration {

    @Bean
    public ObjectMapper objectMapper(SimpleModule javaTimeModule) {
        return new JsonMapper() {{
            registerModule(javaTimeModule);
            setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
            setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }};
    }

    @Bean
    public SimpleModule javaTimeModule() {
        return new JavaTimeModule()
            .addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ISO_DATE))
            .addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ISO_DATE))
            .addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializerConverter())
            .addDeserializer(LocalDateTime.class, new CustomLocalDateTimeDeserializerConverter())
            /*.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
            .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME))*/;
    }

}

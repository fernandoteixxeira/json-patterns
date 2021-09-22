package com.github.fernando.jsonpatterns.application.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CustomLocalDateTimeDeserializerConverter extends StdDeserializer<LocalDateTime> {

    protected CustomLocalDateTimeDeserializerConverter() {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(final JsonParser parser, final DeserializationContext context) throws IOException, JsonProcessingException {
        return convert(parser.getText());
    }

    private LocalDateTime convert(String localDateTime) {
        if (Objects.isNull(localDateTime) || localDateTime.isEmpty()) {
            return null;
        }
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(localDateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        if (!zonedDateTime.getZone().equals(ZoneOffset.UTC)) {
            return toUTC(zonedDateTime.toLocalDateTime(), zonedDateTime.getZone());
        }

        return zonedDateTime.toLocalDateTime();
    }

    private static LocalDateTime toUTC(LocalDateTime localDateTime, ZoneId zoneId) {
        return localDateTime.atZone(zoneId)
            .withZoneSameInstant(ZoneOffset.UTC)
            .toLocalDateTime();
    }

}
package br.com.beblue.resources;

import br.com.beblue.domain.disc.Genre;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public final class TestsConstants {

    private TestsConstants() {
    }

    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) ->
                    new JsonPrimitive(src.format(ISO_LOCAL_DATE_TIME)))
            .setPrettyPrinting()
            .create();


}

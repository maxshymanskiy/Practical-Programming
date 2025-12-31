package com.theatre.app.model;

import static com.theatre.app.util.validation.ModelValidator.*;

import java.time.Duration;

public record Movie(
        String id,
        String title,
        Duration duration
) {
    public Movie {
        validateNotBlank(id, "Movie ID");
        validateNotBlank(title, "Movie title");
        validatePositive(duration, "Movie duration");
    }
}
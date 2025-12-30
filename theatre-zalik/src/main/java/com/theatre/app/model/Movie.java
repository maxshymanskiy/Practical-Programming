package com.theatre.app.model;

import static com.theatre.app.validation.ModelValidator.*;

import lombok.NonNull;

import java.time.Duration;

public record Movie(
        @NonNull String id,
        @NonNull String title,
        @NonNull Duration duration
) {
    public Movie {
        validateNotBlank(id, "Movie ID");
        validateNotBlank(title, "Movie title");
        validatePositive(duration, "Movie duration");
    }
}
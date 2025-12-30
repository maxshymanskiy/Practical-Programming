package com.theatre.app.model;

import com.theatre.app.validation.ModelValidator;
import lombok.NonNull;

import java.time.Duration;

// NOTE: Records are perfect for immutable value objects like Movie
public record Movie(
        @NonNull String id,
        @NonNull String title,
        @NonNull Duration duration
) {
    // Compact constructor delegates to centralized validation
    public Movie {
        ModelValidator.validateNotBlank(id, "Movie ID");
        ModelValidator.validateNotBlank(title, "Movie title");
        ModelValidator.validatePositive(duration, "Movie duration");
    }
}


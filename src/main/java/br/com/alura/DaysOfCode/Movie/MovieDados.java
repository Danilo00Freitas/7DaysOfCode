package br.com.alura.DaysOfCode.Movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record MovieDados(

        @NotBlank @JsonProperty("original_title")
        String titulo,
        @NotBlank @JsonProperty("poster_path")
        String imgUrl,
        @NotBlank @JsonProperty("vote_average")
        Double nota,
        @NotBlank @JsonProperty("release_date")
        int ano
) {
}

package dev.affoysal.tmdb_cli_tool.Client;

import dev.affoysal.tmdb_cli_tool.Model.TMDBResponse;
import org.springframework.web.service.annotation.GetExchange;

public interface TMDBClient {

    @GetExchange("/now_playing?language=en-US&page=1")
    TMDBResponse nowPlaying();

    @GetExchange("/popular?language=en-US&page=1")
    TMDBResponse popular();

    @GetExchange("/top_rated?language=en-US&page=1")
    TMDBResponse topRated();

    @GetExchange("/upcoming?language=en-US&page=1")
    TMDBResponse upcoming();

}

package dev.affoysal.tmdb_cli_tool.Model;

import java.util.List;

public record TMDBResponse(int page, int total_pages, int total_results,
                           List<Movie> results, String statusMessage) {
}

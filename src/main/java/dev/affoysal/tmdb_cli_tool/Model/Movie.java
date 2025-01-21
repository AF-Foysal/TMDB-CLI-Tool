package dev.affoysal.tmdb_cli_tool.Model;

public record Movie(boolean adult, String backdrop_path, long id,
                    String original_language, String original_title,
                    String overview, double popularity,
                    String poster_path, String release_date,
                    String title, boolean video, double vote_average, long vote_count) {
}
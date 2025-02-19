package dev.affoysal.tmdb_cli_tool.Commands;

import dev.affoysal.tmdb_cli_tool.Client.TMDBClient;
import dev.affoysal.tmdb_cli_tool.Model.Movie;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.springframework.shell.table.*;

import java.util.LinkedHashMap;
import java.util.List;

@Command(command = "tmdb-app")
public class TMDBCommands {

    private final TMDBClient tmdbClient;

    public TMDBCommands(TMDBClient tmdbClient) {
        this.tmdbClient = tmdbClient;
    }

    @Command(command = "", description = "Show the popular, top-rated, upcoming and now playing movies " +
            "from the TMDB API. Valid options are ('playing','popular','top' & 'upcoming')")
    public Object getMoviesByType(
            @Option(required = true) String type) {
        try {
            if (type.equalsIgnoreCase("playing")) {
                List<Movie> movies = tmdbClient.nowPlaying().results();
                return buildTable(movies);
            } else if (type.equalsIgnoreCase("popular")) {
                List<Movie> movies = tmdbClient.popular().results();
                return buildTable(movies);
            } else if (type.equalsIgnoreCase("top")) {
                List<Movie> movies = tmdbClient.topRated().results();
                return buildTable(movies);
            } else if (type.equalsIgnoreCase("upcoming")) {
                List<Movie> movies = tmdbClient.upcoming().results();
                return buildTable(movies);
            } else {
                return "Invalid type value. Allowed values: 'playing', 'popular', 'top' & 'upcoming'";
            }
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }

    private Table buildTable(List<Movie> movies) {
        TableModel tableModel = new BeanListTableModel<>(
                movies,
                new LinkedHashMap<>() {{
                    put("original_title", "Title");
                    put("original_language", "Language");
                    put("vote_average", "Rating");
                    put("release_date", "Released Date");
                }}
        );
        TableBuilder tableBuilder = new TableBuilder(tableModel);
        tableBuilder.addFullBorder(BorderStyle.oldschool);
        tableBuilder.addHeaderBorder(BorderStyle.oldschool);

        return tableBuilder.build();
    }
}



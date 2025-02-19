package dev.affoysal.tmdb_cli_tool;

import dev.affoysal.tmdb_cli_tool.Client.TMDBClient;
import dev.affoysal.tmdb_cli_tool.Commands.TMDBCommands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.command.annotation.EnableCommand;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@EnableCommand(TMDBCommands.class)
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Value("${tmdb.api.base.url}")
	private String baseURL;

	@Value("${tmdb.api.bearer.token}")
	private String token;

	@Bean
	TMDBClient tmdbClient(){
		WebClient client = WebClient.builder()
				.baseUrl(baseURL)
				.defaultHeaders(headers -> {
					headers.add("Accept", "application/json");
					headers.add("Authorization", "Bearer " + token);
				}).build();

		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(client)).build();
		return factory.createClient(TMDBClient.class);
	}


}

package br.com.luis.apifilmes;

import static br.com.luis.apifilmes.utils.ApiApplicationUtils.*;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class ApiApplication {
	private static ConfigurableApplicationContext context;
	private static List<Integer> quantidadeDeFilmesNasListas = getQuantidadeDeFilmesAtualizada();

	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	public void restartApplication() {
		if (identificarAtualizacaoEmAlgumaDasListas(quantidadeDeFilmesNasListas)) {
			restart();
		}
	}

	public static void main(String[] args) {
		context = SpringApplication.run(ApiApplication.class, args);
	}

	public static void restart() {
		ApplicationArguments args = context.getBean(ApplicationArguments.class);

		Thread thread = new Thread(() -> {
			context.close();
			context = SpringApplication.run(ApiApplication.class, args.getSourceArgs());
		});

		thread.setDaemon(false);
		thread.start();
	}
}

package br.com.example.ScreenMatch;

import br.com.example.ScreenMatch.Models.DadosSeries;
import br.com.example.ScreenMatch.Services.ConsumoApi;
import br.com.example.ScreenMatch.Services.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConsumoApi consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=51329da0");
		System.out.println(json);
		ConverteDados conversor = new ConverteDados();
		DadosSeries infoSerie = conversor.obterDados(json, DadosSeries.class);
		System.out.println(infoSerie);
	}
}

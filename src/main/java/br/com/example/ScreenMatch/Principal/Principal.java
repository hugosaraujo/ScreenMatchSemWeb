package br.com.example.ScreenMatch.Principal;

import br.com.example.ScreenMatch.Models.DadosSeries;
import br.com.example.ScreenMatch.Models.DadosTemporadas;
import br.com.example.ScreenMatch.Services.ConsumoApi;
import br.com.example.ScreenMatch.Services.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitor = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=51329da0";

    public void exibMenu() {
        System.out.print("Digite a série que quer pesquisar: ");
        String nomeSerie = leitor.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSeries infoSerie = conversor.obterDados(json, DadosSeries.class);
        System.out.println(infoSerie);

        List<DadosTemporadas> temporadas = new ArrayList<>();
		for (int i = 1; i <= infoSerie.totalTemporadas(); i++) {
			json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") +"&Season=" + i + API_KEY);
			DadosTemporadas infoTemporada = conversor.obterDados(json, DadosTemporadas.class);
			temporadas.add(infoTemporada);
		}
		temporadas.forEach(System.out::println);

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

    }
}

package br.com.example.ScreenMatch.Services;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}

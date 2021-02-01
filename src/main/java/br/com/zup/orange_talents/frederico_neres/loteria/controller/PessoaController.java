package br.com.zup.orange_talents.frederico_neres.loteria.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("api/pessoas")
public class PessoaController {

    @GetMapping
    public String gerarNumerosAposta() {
        List<Integer> cartelaDeNumeros = IntStream.range(1, 61).boxed().collect(Collectors.toList());
        Collections.shuffle(cartelaDeNumeros);
        List<Integer> numerosSorteados = cartelaDeNumeros.subList(0, 6);
        Collections.sort(numerosSorteados);
        return numerosSorteados.stream().map(numero->numero.toString()).collect(Collectors.joining(", "));
    }

    @GetMapping("/{pessoaEmail}")
    public List<String> listarApostasPeloEmail(@PathVariable("pessoaEmail") String pessoaEmail) {
        return new ArrayList<>();
    }
}

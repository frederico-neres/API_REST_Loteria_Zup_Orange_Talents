package br.com.zup.orange_talents.frederico_neres.loteria.controller;

import br.com.zup.orange_talents.frederico_neres.loteria.controller.response.Response;
import br.com.zup.orange_talents.frederico_neres.loteria.dto.PessoaNumerosApostaInputDTO;
import br.com.zup.orange_talents.frederico_neres.loteria.dto.PessoaNumerosApostaOutputDTO;
import br.com.zup.orange_talents.frederico_neres.loteria.dto.PessoaSalvarInputDTO;
import br.com.zup.orange_talents.frederico_neres.loteria.dto.PessoaSalvarOutputDTO;
import br.com.zup.orange_talents.frederico_neres.loteria.model.Aposta;
import br.com.zup.orange_talents.frederico_neres.loteria.model.Pessoa;
import br.com.zup.orange_talents.frederico_neres.loteria.repository.ApostaRepository;
import br.com.zup.orange_talents.frederico_neres.loteria.repository.PessoaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/pessoas")
public class PessoaController {

    public PessoaRepository pessoaRepository;
    public ApostaRepository apostaRepository;

    public PessoaController(PessoaRepository pessoaRepository, ApostaRepository apostaRepository) {
        this.pessoaRepository = pessoaRepository;
        this.apostaRepository = apostaRepository;
    }

    @GetMapping
    public ResponseEntity<Response<List<PessoaSalvarOutputDTO>>> listarTodos() {
        Response<List<PessoaSalvarOutputDTO>> response = new Response<List<PessoaSalvarOutputDTO>>();

        List<Pessoa> pessoas = pessoaRepository.findAll();
        List<PessoaSalvarOutputDTO> pessoaOutputDTOList = pessoas.stream().map(pessoa -> {
            PessoaSalvarOutputDTO pessoaOutputDTO = new PessoaSalvarOutputDTO();
            pessoaOutputDTO.setId(pessoa.getId());
            pessoaOutputDTO.setNome(pessoa.getNome());
            pessoaOutputDTO.setCpf(pessoa.getCpf());
            pessoaOutputDTO.setEmail(pessoa.getEmail());
            return pessoaOutputDTO;
        }).collect(Collectors.toList());

        response.setData(pessoaOutputDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{pessoaEmail}")
    public ResponseEntity<Response<List<PessoaNumerosApostaOutputDTO>>> listarApostasPeloEmail(
            @PathVariable("pessoaEmail") String pessoaEmail) {

        Response<List<PessoaNumerosApostaOutputDTO>> response = new Response<List<PessoaNumerosApostaOutputDTO>>();
        Pessoa pessoa = pessoaRepository.findByEmail(pessoaEmail).orElse(null);

        if(pessoa == null) {
            response.getErrors().add("Não existe nenhuma pessoa cadastrada com esse e-mail");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        List<Aposta> apostas = pessoa.getApostas();

        List<PessoaNumerosApostaOutputDTO> pessoaOutputDTOList =
                apostas.stream().map(aposta-> {
                    PessoaNumerosApostaOutputDTO pessoaOutputDTO = new PessoaNumerosApostaOutputDTO();
                    pessoaOutputDTO.setId(aposta.getId());
                    pessoaOutputDTO.setNumerosAposta(aposta.getNumerosAposta());
                    pessoaOutputDTO.setPessoaId(aposta.getPessoa().getId());
                    pessoaOutputDTO.setPessoaNome(aposta.getPessoa().getNome());
                    return pessoaOutputDTO;
                }).collect(Collectors.toList());

        response.setData(pessoaOutputDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Response<PessoaSalvarOutputDTO>> salvarPessoa(@RequestBody @Valid PessoaSalvarInputDTO pessoaInputDTO,
                                         BindingResult result) {
        Response<PessoaSalvarOutputDTO> response = new Response<PessoaSalvarOutputDTO>();
        if(result.hasErrors()) {
            result.getAllErrors().stream().forEach(error-> {
                response.getErrors().add(error.getDefaultMessage());
            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Pessoa pessoa = pessoaRepository.save(pessoaInputDTO.criarPessoa());
        PessoaSalvarOutputDTO pessoaOutputDTO = new PessoaSalvarOutputDTO();
        pessoaOutputDTO.setId(pessoa.getId());
        pessoaOutputDTO.setNome(pessoa.getNome());
        pessoaOutputDTO.setCpf(pessoa.getCpf());
        pessoaOutputDTO.setEmail(pessoa.getEmail());

        response.setData(pessoaOutputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/numeros-aposta")
    public ResponseEntity<Response<PessoaNumerosApostaOutputDTO>> gerarNumerosAposta(
            @RequestBody @Valid PessoaNumerosApostaInputDTO pessoaInputDTO) {

        Response<PessoaNumerosApostaOutputDTO> response = new Response<PessoaNumerosApostaOutputDTO>();
        Pessoa pessoa = pessoaRepository.findByEmail(pessoaInputDTO.getEmail()).orElse(null);

        if(pessoa == null) {
            response.getErrors().add("Não existe nenhuma pessoa cadastrada com esse e-mail");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Aposta aposta = new Aposta();
        aposta.gerarNumerosAposta();
        aposta.setPessoa(pessoa);

        aposta = apostaRepository.save(aposta);
        PessoaNumerosApostaOutputDTO pessoaOutputDTO = new PessoaNumerosApostaOutputDTO();
        pessoaOutputDTO.setId(aposta.getId());
        pessoaOutputDTO.setNumerosAposta(aposta.getNumerosAposta());
        pessoaOutputDTO.setPessoaId(aposta.getPessoa().getId());
        pessoaOutputDTO.setPessoaNome(aposta.getPessoa().getNome());

        response.setData(pessoaOutputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}

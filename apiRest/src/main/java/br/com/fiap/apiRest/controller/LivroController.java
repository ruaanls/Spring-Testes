package br.com.fiap.apiRest.controller;

import br.com.fiap.apiRest.DTO.LivroRequest;
import br.com.fiap.apiRest.DTO.LivroResponse;
import br.com.fiap.apiRest.Service.LivroService;
import br.com.fiap.apiRest.model.Livro;
import br.com.fiap.apiRest.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/livros")
public class LivroController
{
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private LivroService service;

    @PostMapping
    public ResponseEntity<Livro> createLivro(@RequestBody LivroRequest livro){
        LivroService serviceLivro = new LivroService();
        Livro livroConvertido = serviceLivro.requestToLivro(livro);
        Livro livroSalvo = livroRepository.save(livroConvertido);
        return new ResponseEntity<>(livroSalvo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LivroResponse>> getLivros()
    {
        List<Livro> livros = livroRepository.findAll();
        List<LivroResponse> listaLivros = new ArrayList<>();
        for(Livro livro: livros)
        {
            listaLivros.add(service.livroToResponse(livro));
        }
        return new ResponseEntity<>(listaLivros, HttpStatus.OK);
    }

    @GetMapping("/{id}")

    public ResponseEntity<LivroResponse> livroByID(@PathVariable Long id)
    {
        Optional<Livro> livro = livroRepository.findById(id);
        LivroResponse LivroResponse = service.livroOptionalToResponse(livro);

        return new ResponseEntity<>(LivroResponse,HttpStatus.OK);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Livro> updateLivro(@RequestBody Livro livroNovo, @PathVariable long id)
    {
        Optional<Livro> livroVelho = livroRepository.findById(id);
        if(livroVelho.isEmpty())
        {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        livroNovo.setId(livroVelho.get().getId());
        Livro livroSalvo = livroRepository.save(livroNovo);

        return new ResponseEntity<>(livroSalvo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivro(@PathVariable long id)
    {
        livroRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

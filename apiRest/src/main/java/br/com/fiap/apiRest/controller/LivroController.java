package br.com.fiap.apiRest.controller;

import br.com.fiap.apiRest.DTO.LivroRequest;
import br.com.fiap.apiRest.DTO.LivroResponse;
import br.com.fiap.apiRest.Service.LivroService;
import br.com.fiap.apiRest.model.Livro;
import br.com.fiap.apiRest.repository.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/livros")
public class LivroController
{
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private LivroService service;

    @PostMapping
    public ResponseEntity<Livro> createLivro(@RequestBody @Valid LivroRequest livro){
        LivroService serviceLivro = new LivroService();
        Livro livroConvertido = serviceLivro.requestToLivro(livro);
        Livro livroSalvo = livroRepository.save(livroConvertido);
        return new ResponseEntity<>(livroSalvo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<LivroResponse>> getLivros(@RequestParam(defaultValue = "0") Integer pageNumber)
    {

        Pageable pageable = PageRequest.of(pageNumber,2, Sort.by("titulo").ascending());
        Page<LivroResponse> livros = service.findall(pageable);
        for(LivroResponse livro : livros)
        {
            livro.setLink(
                    linkTo(
                            methodOn(LivroController.class)
                                    .livroByID(livro.getId())
                    ).withSelfRel()
            );
        }
        return new ResponseEntity<>(service.findall(pageable), HttpStatus.OK);
    }


    @GetMapping("/page/{id}")
    public ResponseEntity<Page<LivroResponse>> getLivros2(@PathVariable int id)
    {

        Pageable pageable = PageRequest.of(id,2, Sort.by("titulo").ascending());
        Page<LivroResponse> livros = service.findall(pageable);
        for(LivroResponse livro : livros)
        {
            livro.setLink(
                    linkTo(
                            methodOn(LivroController.class)
                                    .livroByID(livro.getId())
                    ).withSelfRel()
            );
        }

        return new ResponseEntity<>(service.findall(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")

    public ResponseEntity<LivroResponse> livroByID(@PathVariable Long id)
    {
        Optional<Livro> livroCapturado = livroRepository.findById(id);
        if(livroCapturado.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LivroResponse LivroResponse = service.livroToResponse(livroCapturado.get());
        LivroResponse.setLink(
                linkTo(
                        methodOn(LivroController.class)
                                .getLivros(0)
                ).withRel("Todos os Livros")
        );

        return new ResponseEntity<>(LivroResponse,HttpStatus.OK);
    }


    @PutMapping ("/{id}")
    public ResponseEntity<Livro> updateLivro(@RequestBody LivroRequest livroNovo, @PathVariable Long id)
    {
        Optional<Livro> livroVelho = livroRepository.findById(id);
        if(livroVelho.isEmpty())
        {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        Livro livroConvertido = service.requestToLivro(livroNovo);
        livroConvertido.setId(livroVelho.get().getId());
        Livro livroSalvo = livroRepository.save(livroConvertido);

        return new ResponseEntity<>(livroSalvo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivro(@PathVariable long id)
    {
        livroRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

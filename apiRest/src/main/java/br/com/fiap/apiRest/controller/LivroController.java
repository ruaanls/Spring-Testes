package br.com.fiap.apiRest.controller;

import br.com.fiap.apiRest.model.Livro;
import br.com.fiap.apiRest.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/livros")
public class LivroController
{
    @Autowired
    private LivroRepository livroRepository;

    @PostMapping
    public ResponseEntity<Livro> createLivro(@RequestBody Livro livro){
        Livro livroSalvo = livroRepository.save(livro);
        return new ResponseEntity<>(livroSalvo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> getLivros()
    {
        List<Livro> livros = livroRepository.findAll();
        return new ResponseEntity<>(livros, HttpStatus.OK);
    }

    @GetMapping("/{id}")

    public ResponseEntity<Livro> livroByID(@PathVariable Long id)
    {
        Optional<Livro> livro = livroRepository.findById(id);
        if(livro.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(livro.get(),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Livro> updateLivro(@RequestBody Livro livro)
    {
        Livro livroSalvo = livroRepository.save(livro);
        return new ResponseEntity<>(livroSalvo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivro(@PathVariable long id)
    {
        livroRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

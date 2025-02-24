package br.com.fiap.apiRest.Service;

import br.com.fiap.apiRest.DTO.LivroRequest;
import br.com.fiap.apiRest.DTO.LivroRequestDTO;
import br.com.fiap.apiRest.DTO.LivroResponse;
import br.com.fiap.apiRest.model.Livro;
import br.com.fiap.apiRest.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService
{
    @Autowired
    LivroRepository livroRepository;
    public Livro requestToLivro(LivroRequest livroRequest)
    {
        Livro livro = new Livro();
        livro.setAutor(livroRequest.getAutor());
        livro.setTitulo(livroRequest.getTitulo());
        livro.setPreco(livroRequest.getPreco());
        livro.setCategoria(livroRequest.getCategoria());
        livro.setIsbn(livroRequest.getIsbn());
        return livro;
    }

    public Livro recordToLivro(LivroRequestDTO livroRecord)
    {
        Livro livro = new Livro();
        livro.setTitulo(livroRecord.titulo());
        livro.setAutor(livroRecord.autor());
        return livro;
    }

    public LivroResponse livroToResponse(Livro livro)
    {
        LivroResponse LivroResponse = new LivroResponse(livro.getTitulo()+" - "+livro.getAutor());
        return LivroResponse;

    }

    public List<LivroResponse> livrosToResponse(List<Livro> livros)
    {
        List<LivroResponse> listaLivros = new ArrayList<>();
        for(Livro livro: livros)
        {
            listaLivros.add(livroToResponse(livro));
        }
        return listaLivros;

    }

    public Page<LivroResponse> findall(Pageable pageable)
    {
        return livroRepository.findAll(pageable).map(livro -> livroToResponse(livro));
    }



}
